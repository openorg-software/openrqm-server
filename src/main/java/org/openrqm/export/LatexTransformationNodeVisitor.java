/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2020 Marcel Jaehn
 */

package org.openrqm.export;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.jsoup.select.NodeVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ImageUtils;

public class LatexTransformationNodeVisitor implements NodeVisitor {
    
    private static final Logger logger = LoggerFactory.getLogger(LatexTransformationNodeVisitor.class);

    String transformedContent = "";
    int currentImageCount = 0;

    boolean tableHasHeader = false;
    String dataUri = "";

    @Override
    public void head(Node node, int i) {
        switch (node.nodeName()) {
            case "body": break; //do nothing
            case "#text": transformedContent += replaceSpecialCharacters(unescape(node.toString())); break;
            case "h2": transformedContent += "\n\\section{"; break;
            case "h3": transformedContent += "\n\\subsection{"; break;
            case "h4": transformedContent += "\n\\subsubsection{"; break;
            case "p": break; //do nothing
            case "strong": transformedContent += "\\textbf{"; break;
            case "i": transformedContent += "\\textit{"; break;
            case "blockquote": transformedContent += "\\begin{quotation}\n"; break;
            case "ul": transformedContent += "\\begin{itemize}\n"; break;
            case "ol": transformedContent += "\\begin{enumerate}\n"; break;
            case "li": transformedContent += "  \\item "; break;
            case "a": transformedContent += "\\href{"+node.attr("href")+"}{"; break;
            case "figure": break; //do nothing
            case "img":
                currentImageCount++;
                dataUri = node.attr("src");
                // there is no figcaption, so save the image now
                if (node.nextSibling() == null || !node.nextSibling().nodeName().equals("figcaption")) {
                    String imageType = ImageUtils.dataUriToImageType(dataUri);
                    transformedContent +=
                        "\\begin{figure}[H]\n" +
                        "  \\centering\n" +
                        "  \\includegraphics[scale=0.7]{images/image" + currentImageCount + "." + imageType + "}\n" +
                        "  \\label{fig:image" + currentImageCount + "}\n";
                    ImageUtils.saveImage(dataUri, imageType, "image" + currentImageCount);
                    dataUri = "";
                }
                break;
            case "figcaption":
                // this figcaption is related to a previous img, so save the image now
                if (node.previousSibling() != null && node.previousSibling().nodeName().equals("img")) {
                    String imageType = ImageUtils.dataUriToImageType(dataUri);
                    String imageName = "image" + currentImageCount + "_" + ImageUtils.replaceInvalidFilenameCharacters(node.childNode(0).toString());
                    transformedContent +=
                        "\\begin{figure}[H]\n" +
                        "  \\centering\n" +
                        "  \\includegraphics[width=0.9\\columnwidth]{images/" + imageName + "." + imageType + "}\n" +
                        "  \\label{fig:image" + currentImageCount + "}\n" +
                        "  \\caption{";
                    ImageUtils.saveImage(dataUri, imageType, imageName);
                    dataUri = "";
                }
                break;
            case "table": transformedContent += "\n\\begin{center}\n\\begin{tabular}"; break;
            case "thead":
                transformedContent += determineTableLayout((Element)node, "th");
                tableHasHeader = true;
                break;
            case "tbody":
                if (!tableHasHeader) {
                    transformedContent += determineTableLayout((Element)node, "td");
                    tableHasHeader = false;
                }
                break;
            case "tr": transformedContent += "\n\\hline\n"; break;
            case "th": transformedContent += "\\textbf{"; break;
            case "td": break; //do nothing
            default: logger.error("Unknown tag in HTML during transformation: " + node.nodeName()); break;
        }
    }

    @Override
    public void tail(Node node, int i) {
        switch (node.nodeName()) {
            case "body": break; //do nothing
            case "#text": break; //do nothing
            case "h2": transformedContent += "}\n\n"; break;
            case "h3": transformedContent += "}\n\n"; break;
            case "h4": transformedContent += "}\n\n"; break;
            case "p":
                if (node.nextSibling() != null && node.nextSibling().nodeName().equals("p")) {
                    transformedContent += "\\\\\n";
                } else {
                    transformedContent += "\n";
                }
                break;
            case "strong": transformedContent += "}"; break;
            case "i": transformedContent += "}"; break;
            case "blockquote": transformedContent += "\\end{quotation}\n"; break;
            case "ul": transformedContent += "\\end{itemize}\n"; break;
            case "ol": transformedContent += "\\end{enumerate}\n"; break;
            case "li": transformedContent += "\n"; break;
            case "a": transformedContent += "}"; break;
            case "figure": break; //do nothing
            case "img": 
                if (node.nextSibling() == null || !node.nextSibling().nodeName().equals("figcaption")) {
                    transformedContent += "\\end{figure}\n";
                };
                break;
            case "figcaption":
                if (node.previousSibling() != null && node.previousSibling().nodeName().equals("img")) {
                    //close the figcaption and the image, it has been delayed because there is a figcaption
                    transformedContent +=
                            "}\n"+
                            "\\end{figure}\n";
                };
                break;
            case "table": transformedContent += "\n\\end{tabular}\n\\captionof{table}{}\n\\end{center}"; break;
            case "tbody": transformedContent += "\n\\hline"; break;
            case "tr": transformedContent += "\\\\"; break;
            case "th":
                // only if not last th in tr
                if (((Element)node).lastElementSibling() != (Element)node) {
                    transformedContent += "} & ";
                } else {
                    transformedContent += "}";
                }
                break;
            case "td":
                // only if not last td in tr
                if (((Element)node).lastElementSibling() != (Element)node) {
                    transformedContent += " & ";
                }
                break;
            default: break;
        }
    }
    
    private String unescape(String text) {
        // unescape the HTML entities. we will have to escape them
        // again to be understandable by LaTeX
        return Parser.unescapeEntities(text, false);
    }
    
    private String replaceSpecialCharacters(String text) {
        text = text
                // replace line breaks, they will be determined by the HTML tags
                .replace("\n", "")
                .replace("\r", "")
                // replace &nbsp; by a normal space
                .replace("\u00a0", " ")
                // this must be done before inserting the escaping backslashes
                .replace("\\", "\\textbackslash ")
                // afterwards replace double quotes, they have to be replaced before umlaute
                .replace("\"", " \\dq ")
                // replace LaTeX special characters
                .replace("$", "\\$")
                .replace("%", "\\%")
                .replace("_", "\\_")
                .replace("}", "\\}")
                .replace("&", "\\&")
                .replace("#", "\\#")
                .replace("{", "\\{")
                .replace("ä", "\\\"a")
                .replace("ü", "\\\"u")
                .replace("ö", "\\\"o")
                .replace("Ä", "\\\"A")
                .replace("Ü", "\\\"U")
                .replace("Ö", "\\\"O")
                .replace("ß", "{\\ss}");
        System.out.println("Text:" + text);
        return text;
    }
    
    private String determineTableLayout(Element element, String tagToCount) {
        String layout = "{|";
        // add 'c|' for every table row
        layout = element.getElementsByTag(tagToCount).stream().map((tableRow) -> "c|").reduce(layout, String::concat);
        return layout+"}";
    }
}

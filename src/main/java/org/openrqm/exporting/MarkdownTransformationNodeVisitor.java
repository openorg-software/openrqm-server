/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2020 Marcel Jaehn
 */

package org.openrqm.exporting;

import org.jsoup.nodes.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ImageUtils;

public class MarkdownTransformationNodeVisitor extends TransformationNodeVisitor {
    protected static Logger logger = LoggerFactory.getLogger(MarkdownTransformationNodeVisitor.class);

    String dataUri = "";

    @Override
    public void head(Node node, int i) {
        switch (node.nodeName()) {
            case "body": break; //do nothing
            case "#text": transformedContent += replaceSpecialCharacters(unescape(node.toString())); break;
            case "h2": transformedContent += "\n## "; break;
            case "h3": transformedContent += "\n### "; break;
            case "h4": transformedContent += "\n#### "; break;
            case "p": break; //do nothing
            case "strong": transformedContent += "**"; break;
            case "i": transformedContent += "_"; break;
            case "blockquote": transformedContent += "\n> "; break;
            case "ul": transformedContent += "<ul>"; break;
            case "ol": transformedContent += "<ol>"; break;
            case "li": transformedContent += "<li>"; break;
            case "a": transformedContent += "["; break;
            case "figure": break; //do nothing
            case "img":
                imageCount++;
                dataUri = node.attr("src");
                // there is no figcaption, so save the image now
                if (node.nextSibling() == null || !node.nextSibling().nodeName().equals("figcaption")) {
                    String imageType = ImageUtils.dataUriToImageType(dataUri);
                    transformedContent += "\n![" + node.attr("alt") + "](images/image" + imageCount + "." + imageType + ")";
                    ImageUtils.saveImage(dataUri, imageType, "image" + imageCount);
                    images.add("images/" + "image" + imageCount + "." + imageType);
                    dataUri = "";
                }
                break;
            case "figcaption":
                // this figcaption is related to a previous img, so save the image now
                if (node.previousSibling() != null && node.previousSibling().nodeName().equals("img")) {
                    String imageType = ImageUtils.dataUriToImageType(dataUri);
                    String imgAlt = node.previousSibling().attr("alt");
                    String imageName = "image" + imageCount + "_" + ImageUtils.replaceInvalidFilenameCharacters(node.childNode(0).toString());
                    transformedContent += "\n![" + imgAlt + "](images/" + imageName + "." + imageType + " " + '"';
                    ImageUtils.saveImage(dataUri, imageType, imageName);
                    images.add("images/" + imageName + "." + imageType);
                    dataUri = "";
                }
                break;
            case "table": transformedContent += "<table>"; break;
            case "tbody": transformedContent += "<tbody>"; break;
            case "tr": transformedContent += "<tr>"; break;
            case "td": transformedContent += "<td>"; break;
            default: logger.error("Unknown tag in HTML during transformation: " + node.nodeName()); break;
        }
    }

    @Override
    public void tail(Node node, int i) {
        switch (node.nodeName()) {
            case "body": break; //do nothing
            case "#text": break; //do nothing
            case "h2": transformedContent += "\n"; break;
            case "h3": transformedContent += "\n"; break;
            case "h4": transformedContent += "\n"; break;
            case "p": transformedContent += "  \n"; break;
            case "strong": transformedContent += "**"; break;
            case "i": transformedContent += "_"; break;
            case "blockquote": transformedContent += "\n"; break;
            case "ul": transformedContent += "</ul>\n"; break;
            case "ol": transformedContent += "</ol>\n"; break;
            case "li": transformedContent += "</li>"; break;
            case "a": transformedContent += "]("+node.attr("href")+")"; break;
            case "figure": break; //do nothing
            case "img": transformedContent += "\n"; break;
            case "figcaption":
                if (node.previousSibling() != null && node.previousSibling().nodeName().equals("img")) {
                    transformedContent += '"' + ")\n";
                };
                break;
            case "table": transformedContent += "</table>\n"; break;
            case "tbody": transformedContent += "</tbody>"; break;
            case "tr": transformedContent += "</tr>"; break;
            case "td": transformedContent += "</td>"; break;
            default: break;
        }
    }

    private String unescape(String text) {
        // no unescaping necessary, Markdown understands HTML (but we could do it)
        return text;
    }
    
    private String replaceSpecialCharacters(String text) {
        // replace line breaks, they will be determined by the HTML tags
        return text.replace("\n", "").replace("\r", "")
                .replace("@linebreak@", "");
    }
}

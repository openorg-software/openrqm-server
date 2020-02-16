/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2020 Marcel Jaehn
 */

package org.openrqm.export;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeVisitor;

public class LatexTransformationNodeVisitor implements NodeVisitor {

    String content = "";

    @Override
    public void head(Node node, int i) {
        switch (node.nodeName()) {
            case "body": break; //do nothing
            case "#text": content += node.toString().replace("\n", "").replace("&nbsp;", "\\ "); break;
            case "h2": content += "\n\\section{"; break;
            case "h3": content += "\n\\subsection{"; break;
            case "h4": content += "\n\\subsubsection{"; break;
            case "p": break; //do nothing
            case "strong": content += "\\textbf{"; break;
            case "i": content += "\\textit{"; break;
            case "blockquote": content += "\n\\begin{quotation}"; break;
            case "ul": content += "\n\\begin{itemize}"; break;
            case "ol": content += "\n\\begin{enumerate}"; break;
            case "li": content += "\n  \\item "; break;
            case "a": content += "\\href{"+node.attr("href")+"}{"; break;
            case "figure": break; //do nothing
            case "table": content += "\n\\begin{center}\n\\begin{tabular}"; break;
            case "tbody":
                String layout = "{|";
                // add 'c|' for every table row
                layout = ((Element)node).getElementsByTag("tr").stream().map((tableRow) -> "c|").reduce(layout, String::concat);
                content += layout+"}";
                break;
            case "tr": content += "\n\\hline\n"; break;
            case "td": break; //do nothing
            default: content += "<" + node.nodeName() + ">"; break;
        }
    }

    @Override
    public void tail(Node node, int i) {
        switch (node.nodeName()) {
            case "#text": break; //do nothing
            case "body": break; //do nothing
            case "h2": content += "}"; break;
            case "h3": content += "}"; break;
            case "h4": content += "}"; break;
            case "p": content += "\\\\"; break;
            case "strong": content += "}"; break;
            case "i": content += "}"; break;
            case "blockquote": content += "\n\\end{quotation}"; break;
            case "ul": content += "\n\\end{itemize}"; break;
            case "ol": content += "\n\\end{enumerate}"; break;
            case "li": break; //do nothing
            case "a": content += "}"; break;
            case "figure": break; //do nothing
            case "table": content += "\n\\end{tabular}\n\\captionof{table}{}\n\\end{center}"; break;
            case "tbody": content += "\n\\hline"; break;
            case "tr": content += "\\\\"; break;
            case "td":
                // only if not last td in tr
                if (((Element)node).lastElementSibling() != (Element)node) {
                    content += " & ";
                }
                break;
            default: content += "</" + node.nodeName() + ">\n"; break;
        }
    }
}

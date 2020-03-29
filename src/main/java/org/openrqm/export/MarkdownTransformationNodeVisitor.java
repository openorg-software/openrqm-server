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

public class MarkdownTransformationNodeVisitor implements NodeVisitor {
    
    private static final Logger logger = LoggerFactory.getLogger(MarkdownTransformationNodeVisitor.class);

    String transformedContent = "";

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
            case "img": transformedContent += "\n![" + node.attr("alt") + "][image]"; break;
            case "figcaption": transformedContent += "\n[image] images/image.png " + '"'; break;
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
            case "figcaption": transformedContent += '"'; break;
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
        text = text.replace("\n", "").replace("\r", "");
        System.out.println("Text:" + text);
        return text;
    }
}

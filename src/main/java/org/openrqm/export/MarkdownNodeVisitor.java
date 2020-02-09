/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2020 Marcel Jaehn
 */

package org.openrqm.export;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeVisitor;

/**
 *
 * @author Marcel Jaehn <marcel.jaehn@online.de>
 */
public class MarkdownNodeVisitor implements NodeVisitor {
    String content = "";

    @Override
    public void head(Node node, int i) {
        switch (node.nodeName()) {
            case "#text": content += node.toString().replace("\n", "").replace("\r", "").replace("&nbsp;", " "); break;
            case "body": break; //do nothing
            case "h2": content += "## "; break;
            case "h3": content += "### "; break;
            case "h4": content += "#### "; break;
            case "p": break; //do nothing
            case "strong": content += "**"; break;
            case "i": content += "_"; break;
            case "blockquote": content += "\n\n> "; break;
            case "ul": content += "<ul>"; break;
            case "ol": content += "<ol>"; break;
            case "li": content += "<li>"; break;
            case "a": content += "["; break;
            case "figure": break; //do nothing
            case "table": content += "<table>"; break;
            case "tbody": content += "<tbody>"; break;
            case "tr": content += "<tr>"; break;
            case "td": content += "<td>"; break;
            default: content += "<" + node.nodeName() + ">"; break;
        }
    }

    @Override
    public void tail(Node node, int i) {
        switch (node.nodeName()) {
            case "#text": break; //do nothing
            case "body": break; //do nothing
            case "h2": content += "\n"; break;
            case "h3": content += "\n"; break;
            case "h4": content += "\n"; break;
            case "p": content += "\n\n"; break;
            case "strong": content += "**"; break;
            case "i": content += "_"; break;
            case "blockquote": content += "\n\n"; break;
            case "ul": content += "</ul>\n"; break;
            case "ol": content += "</ol>\n"; break;
            case "li": content += "</li>"; break;
            case "a": content += "]("+node.attr("href")+")"; break;
            case "figure": break; //do nothing
            case "table": content += "</table>"; break;
            case "tbody": content += "</tbody>"; break;
            case "tr": content += "</tr>"; break;
            case "td": content += "</td>"; break;
            default: content += "</" + node.nodeName() + ">"; break;
        }
    }
}

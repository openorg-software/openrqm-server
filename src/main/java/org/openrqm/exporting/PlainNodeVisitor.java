/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2020 Marcel Jaehn
 */

package org.openrqm.exporting;

import org.jsoup.nodes.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlainNodeVisitor extends TransformationNodeVisitor {
    protected static Logger logger = LoggerFactory.getLogger(PlainNodeVisitor.class);

    String content = "";

    @Override
    public void head(Node node, int i) {
        switch (node.nodeName()) {
            case "#text": content += node.toString().replace("\n", "").replace("\r", "").replace("&nbsp;", " "); break;
            case "body": content += "<body>"; break;
            case "h2": content += "<h2>"; break;
            case "h3": content += "<h3>"; break;
            case "h4": content += "<h4>"; break;
            case "p": content += "<p>"; break;
            case "strong": content += "<strong>"; break;
            case "i": content += "<i>"; break;
            case "blockquote": content += "<blockquote>"; break;
            case "ul": content += "<ul>"; break;
            case "ol": content += "<ol>"; break;
            case "li": content += "<li>"; break;
            case "a": content += "<a>"; break;
            case "figure": content += "<figure>"; break;
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
            case "body": content += "</body>"; break;
            case "h2": content += "</h2>"; break;
            case "h3": content += "</h3>"; break;
            case "h4": content += "</h4>"; break;
            case "p": content += "</p>"; break;
            case "strong": content += "</strong>"; break;
            case "i": content += "</i>"; break;
            case "blockquote": content += "</blockquote>"; break;
            case "ul": content += "</ul>"; break;
            case "ol": content += "</ol>"; break;
            case "li": content += "</li>"; break;
            case "a": content += "</a>"; break;
            case "figure": content += "</figure>"; break;
            case "table": content += "</table>"; break;
            case "tbody": content += "</tbody>"; break;
            case "tr": content += "</tr>"; break;
            case "td": content += "</td>"; break;
            default: content += "</" + node.nodeName() + ">"; break;
        }
    }
}

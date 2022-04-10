/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package utils;

import org.jsoup.safety.Safelist;

public class EditorContentSafelist extends Safelist {
    
    private static final Safelist RICHTEXT_WHITELIST =
            new Safelist()
                .addTags("p", "h2", "h3", "h4", "strong", "i", "a", "ul",
                    "li", "ol", "figure", "img", "figcaption", "blockquote",
                    "table", "thead", "tr", "th", "tbody", "td", "oembed")
                .addAttributes("a", "href")
                .addAttributes("figure", "class")
                .addAttributes("img", "src", "alt")
                .addAttributes("td", "colspan", "rowspan")
                .addAttributes("oembed", "url")
                .addProtocols("a", "href", "http", "https")
                .addProtocols("oembed", "url", "http", "https");

    public static Safelist allowedEditorContent() {
        return RICHTEXT_WHITELIST;
    }
}

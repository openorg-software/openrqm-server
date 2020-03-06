/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package utils;

import org.jsoup.safety.Whitelist;

public class EditorContentWhitelist extends Whitelist {

    public static Whitelist allowedEditorContent() {
        return Whitelist.basicWithImages();
    }
}

/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */
package org.openrqm.export;

import com.x5.template.Chunk;
import com.x5.template.Theme;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Marcel Jaehn <marcel.jaehn@online.de>
 */
public class PdfExporter {

    private final static Logger logger = LoggerFactory.getLogger(PdfExporter.class);

    public static void export() throws Exception {
        fillTheme();
        
    }

    private static void fillTheme() {
        Theme theme = new Theme("res", "");
        Chunk c = theme.makeChunk("theme", "html");

        c.set("title", "OpenRQM PDF Export Demo");

        try (FileWriter fw = new FileWriter(new File("res/out.html"))) {
            c.render(fw);
        } catch (IOException ex) {
            logger.error("Exception during html creation", ex);
        }
    }
}

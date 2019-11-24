/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */
package org.openrqm.export;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.openrqm.model.RQMDocument;
import org.openrqm.model.RQMElements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Marcel Jaehn <marcel.jaehn@online.de>
 */
public class PdfExporter {

    private final static Logger logger = LoggerFactory.getLogger(PdfExporter.class);

    public static void export(RQMDocument document, RQMElements elements) throws Exception {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("templates/template.tex");
        
        Map<String, Object> context = new HashMap<>();
        context.put("rqmelements", elements);

        FileWriter writer = new FileWriter("export/export.tex");
        m.execute(writer, context).flush();

        ProcessBuilder pb = new ProcessBuilder("pdflatex", "-shell-escape -synctex=1 -interaction=nonstopmode", "export.tex");
        pb.directory(new File("export"));
        try {
            Process p = pb.start();
            p.waitFor();
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

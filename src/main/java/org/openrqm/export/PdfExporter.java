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

    public static void export(RQMDocument document, RQMElements elements, String templatePath, String exportPath) throws Exception {
        logger.info("Load template");
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile(templatePath);
        logger.info("Template loaded");
        
        Map<String, Object> context = new HashMap<>();
        context.put("rqmelements", elements);

        logger.info("Filling template");
        File exportFile = new File(exportPath);
        exportFile.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(exportFile)) {
            m.execute(writer, context).flush();
        }
        // do again, so the table of contents is created
        try (FileWriter writer = new FileWriter(exportFile)) {
            m.execute(writer, context).flush();
        }
        logger.info("Template filled");

        logger.info("Convert to PDF");
        //-shell-escape -synctex=1 -interaction=nonstopmode
        ProcessBuilder pb = new ProcessBuilder("pdflatex", "export.tex");
        pb.inheritIO().directory(new File("export"));
        Process p = pb.start();
        p.waitFor();
        logger.info("Converted to PDF");
        
    }
}

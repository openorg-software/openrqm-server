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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.NodeTraversor;
import org.openrqm.model.RQMDocument;
import org.openrqm.model.RQMElement;
import org.openrqm.model.RQMElements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class PdfExporter implements Exporter {

    private static final Logger logger = LoggerFactory.getLogger(PdfExporter.class);

    private static final String TEMPLATE_DIR = "templates/";
    private static final String EXPORT_DIR = "export/";

    @Override
    public Resource export(RQMDocument document, RQMElements elements, String templateName, String exportName) throws Exception {
        // load template file to fill with the document
        logger.info("Load template");
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile(TEMPLATE_DIR + templateName + ".tex");
        logger.info("Template loaded successful");

        // transform styling in all elements
        elements.forEach((element) -> {transformElement(element);} );

        // fill the template file with the content of the document
        logger.info("Filling template");
        Map<String, Object> context = new HashMap<>();
        context.put("rqmelements", elements);
        File generatedFile = new File(EXPORT_DIR + exportName + ".tex");
        generatedFile.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(generatedFile)) {
            m.execute(writer, context).flush();
        }
        logger.info("Template filled successful");

        // convert the generated file to pdf
        logger.info("Convert to pdf");
        ProcessBuilder pb = new ProcessBuilder("pdflatex",exportName + ".tex"); //-shell-escape -interaction=nonstopmode
        //pb.inheritIO();
        pb.directory(new File(EXPORT_DIR));
        pb.start().waitFor();
        pb.start().waitFor(); // run twice to generate table of contents
        logger.info("Conversion to pdf successful");
        return new FileSystemResource(EXPORT_DIR + exportName + ".pdf");
    }

    public void transformElement(RQMElement element) {
        Document document = Jsoup.parseBodyFragment(element.getContent());
        if (document == null) {
            logger.info("Error while parsing the element content of elementId " + element.getId());
            return;
        }
        LatexTransformationNodeVisitor transformation = new LatexTransformationNodeVisitor();
        NodeTraversor.traverse(transformation, document.body());
        element.setContent(transformation.content);
    }
}

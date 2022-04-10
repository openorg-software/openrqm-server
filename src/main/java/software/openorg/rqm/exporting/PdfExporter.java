/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package software.openorg.rqm.exporting;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import software.openorg.rqm.model.RQMDocument;
import software.openorg.rqm.model.RQMElement;
import software.openorg.rqm.model.RQMTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class PdfExporter extends Exporter {
    protected static Logger logger = LoggerFactory.getLogger(PdfExporter.class);

    @Override
    public Resource export(RQMDocument document, List<RQMElement> elements, RQMTemplate template, String exportName) throws Exception {
        // load template file to fill with the document
        logger.info("Load template");
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile(TEMPLATE_DIR + template.getName() + ".tex");
        logger.info("Template loaded successful");

        // transform styling in all elements
        LatexTransformationNodeVisitor transformationNodeVisitor = new LatexTransformationNodeVisitor();
        elements.forEach((element) -> {transformElement(transformationNodeVisitor, element);} );

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
        pb.inheritIO();
        pb.directory(new File(EXPORT_DIR));
        pb.start().waitFor();
        pb.start().waitFor(); // run twice to generate table of contents
        logger.info("Conversion to pdf successful");
        return new FileSystemResource(EXPORT_DIR + exportName + ".pdf");
    }
}

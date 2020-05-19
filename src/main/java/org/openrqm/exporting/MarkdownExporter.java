/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.exporting;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.openrqm.model.RQMDocument;
import org.openrqm.model.RQMElement;
import org.openrqm.model.RQMTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class MarkdownExporter extends Exporter {
    protected static Logger logger = LoggerFactory.getLogger(MarkdownExporter.class);

    @Override
    public Resource export(RQMDocument document, List<RQMElement> elements, RQMTemplate template, String exportName) throws Exception {
        // load template file to fill with the document
        logger.info("Load template");
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile(TEMPLATE_DIR + template.getName() + ".md");
        logger.info("Template loaded successful");
        
        // transform styling in all elements
        MarkdownTransformationNodeVisitor transformationNodeVisitor = new MarkdownTransformationNodeVisitor();
        elements.forEach((element) -> {transformElement(transformationNodeVisitor, element);} );
        
        // fill the template file with the content of the document
        logger.info("Filling template");
        Map<String, Object> context = new HashMap<>();
        context.put("rqmelements", elements);
        File generatedFile = new File(EXPORT_DIR + exportName + ".md");
        generatedFile.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(generatedFile)) {
            m.execute(writer, context).flush();
        }
        logger.info("Template filled successfully");
        
        //creating the Zip file
        logger.info("Creating zip file");
        File exportZip = new File(EXPORT_DIR + exportName + ".zip");
        exportZip.getParentFile().mkdirs();
        ZipEntry zipEntry;
        try (FileOutputStream fos = new FileOutputStream(exportZip);
                ZipOutputStream zipOut = new ZipOutputStream(fos)) {
            
            // add markdown file
            zipEntry = new ZipEntry(exportName + ".md");
            zipOut.putNextEntry(zipEntry);
            writeFileToZip(zipOut, EXPORT_DIR + exportName + ".md");
            
            // add images
            for (String imagepath : transformationNodeVisitor.images) {
                zipEntry = new ZipEntry(imagepath);
                zipOut.putNextEntry(zipEntry);
                writeFileToZip(zipOut, EXPORT_DIR + imagepath);
            }
        }
        logger.info("Zip file created successfully");

        return new FileSystemResource(EXPORT_DIR + exportName + ".zip");
    }
    
    private void writeFileToZip(ZipOutputStream zipOut, String filepath) throws FileNotFoundException, IOException {
        try (FileInputStream fis = new FileInputStream(filepath)) {
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        }
    }
}

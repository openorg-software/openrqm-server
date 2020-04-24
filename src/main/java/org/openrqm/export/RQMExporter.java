/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.export;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.openrqm.model.RQMDocument;
import org.openrqm.model.RQMElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class RQMExporter implements Exporter {

    private static final Logger logger = LoggerFactory.getLogger(RQMExporter.class);

    private static final String EXPORT_DIR = "export/";

    @Override
    public Resource export(RQMDocument document, List<RQMElement> elements, String templateName, String exportName) throws Exception {

        logger.info("Export OpenRQM document");
        
        // export document and elements to zip
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false); // objectMapper shall not close the ZipOutputStream
        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
        File exportZip = new File(EXPORT_DIR + exportName + ".zip");
        exportZip.getParentFile().mkdirs();
        ZipEntry zipEntry;

        try (FileOutputStream fos = new FileOutputStream(exportZip);
                ZipOutputStream zipOut = new ZipOutputStream(fos)) {
            
            // serialize document
            zipEntry = new ZipEntry(exportName + "_document.json");
            zipOut.putNextEntry(zipEntry);
            objectMapper.writeValue(zipOut, document);
            
            // serialize elements
            zipEntry = new ZipEntry(exportName + "_elements.json");
            zipOut.putNextEntry(zipEntry);
            objectMapper.writeValue(zipOut, elements);
        }

        // import list of elements
        /*File importFile = new File(EXPORT_DIR + exportName + "_elements.json");
        String contents = new String(Files.readAllBytes(importFile.toPath()));
        List<RQMElement> elementsImport = objectMapper.readValue(contents, new TypeReference<List<RQMElement>>(){});
        System.out.println(elementsImport);*/

        logger.info("Exported OpenRQM document successful");
        
        return new FileSystemResource(EXPORT_DIR + exportName + ".zip");
    }

}

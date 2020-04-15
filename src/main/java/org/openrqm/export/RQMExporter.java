/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.export;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
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
        
        // export to json file ...
        ObjectMapper objectMapper = new ObjectMapper();
        File exportFile = new File(EXPORT_DIR + exportName + ".json");
        exportFile.getParentFile().mkdirs();
        objectMapper.writeValue(exportFile, elements);
        
        // ... and import again
        File importFile = new File(EXPORT_DIR + exportName + ".json");
        String contents = new String(Files.readAllBytes(importFile.toPath()));
        List<RQMElement> elementsImport = objectMapper.readValue(contents, new TypeReference<List<RQMElement>>(){});
        System.out.println(elementsImport);

        logger.info("Exported OpenRQM document successful");
        
        return new FileSystemResource(EXPORT_DIR + exportName + ".json");
    }

}

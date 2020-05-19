/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.importing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import org.openrqm.model.RQMElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RQMImporter implements Importer {
    
    private static final Logger logger = LoggerFactory.getLogger(RQMImporter.class);

    @Override
    public void importRQMFile(String importFilePath) throws Exception {
        logger.info("Export OpenRQM document");
        
        ObjectMapper objectMapper = new ObjectMapper();
        
        // import list of elements
        File importFile = new File(importFilePath);
        String contents = new String(Files.readAllBytes(importFile.toPath()));
        List<RQMElement> elementsImport = objectMapper.readValue(contents, new TypeReference<List<RQMElement>>(){});
        System.out.println(elementsImport);
        
        logger.info("Exported OpenRQM document successful");
    }

}

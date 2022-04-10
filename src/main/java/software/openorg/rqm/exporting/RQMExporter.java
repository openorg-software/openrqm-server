/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package software.openorg.rqm.exporting;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import software.openorg.rqm.model.RQMDocument;
import software.openorg.rqm.model.RQMElement;
import software.openorg.rqm.model.RQMTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class RQMExporter extends Exporter {
    protected static Logger logger = LoggerFactory.getLogger(RQMExporter.class);

    @Override
    public Resource export(RQMDocument document, List<RQMElement> elements, RQMTemplate template, String exportName) throws Exception {

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

        logger.info("Exported OpenRQM document successful");
        
        return new FileSystemResource(EXPORT_DIR + exportName + ".zip");
    }
}

/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package software.openorg.rqm.api;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import software.openorg.rqm.exporting.Exporter;
import software.openorg.rqm.exporting.MarkdownExporter;
import software.openorg.rqm.exporting.PdfExporter;
import software.openorg.rqm.exporting.RQMExporter;
import software.openorg.rqm.model.RQMDocument;
import software.openorg.rqm.model.RQMElement;
import software.openorg.rqm.model.RQMTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExportApiController implements ExportApi {

    private static final Logger logger = LoggerFactory.getLogger(ExportApiController.class);

    @Override
    public ResponseEntity<Resource> exportMarkdown(
            @NotNull @Parameter(name = "documentId", description = "The document to export", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "documentId", required = true) Long documentId,
            @NotNull @Parameter(name = "templateId", description = "The template to use for the export", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "templateId", required = true) Long templateId) {
        return export(new MarkdownExporter(), documentId, templateId);
    }

    @Override
    public ResponseEntity<Resource> exportPdf(
            @NotNull @Parameter(name = "documentId", description = "The document to export", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "documentId", required = true) Long documentId,
            @NotNull @Parameter(name = "templateId", description = "The template to use for the export", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "templateId", required = true) Long templateId) {
        return export(new PdfExporter(), documentId, templateId);
    }
    
    @Override
    public ResponseEntity<Resource> exportRaw(
            @NotNull @Parameter(name = "documentId", description = "The document to export", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "documentId", required = true) Long documentId) {
        return export(new RQMExporter(), documentId, null);
    }

    @Override
    public ResponseEntity<List<RQMTemplate>> getMarkdownTemplates() {
        try {
            List<RQMTemplate> templates = null;
            return new ResponseEntity<>(templates, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<RQMTemplate>> getPdfTemplates() {
        try {
            List<RQMTemplate> templates = null;
            return new ResponseEntity<>(templates, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    private ResponseEntity<Resource> export(Exporter exporter, Long documentId, Long templateId) {
        logger.info("Starting export");
        try {
            RQMTemplate template = retrieveTemplateFromDatabase(templateId);
            RQMDocument document = retrieveDocumentFromDatabase(documentId);
            List<RQMElement> elements = retrieveElementsFromDatabase(documentId);

            Resource exportResource = exporter.export(document, elements, template, "export");
            
            if (exportResource != null && exportResource.exists()) {
                logger.info("Finished export successful");
                return new ResponseEntity<>(exportResource, HttpStatus.OK);
            } else {
                logger.error("No exported document can be returned");
            }
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
        }
        logger.info("Failed to export");
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private RQMTemplate retrieveTemplateFromDatabase(Long templateId) {
        if (templateId == null) {
            return null;
        }
        logger.info("Getting template from database");
        RQMTemplate template = null;
        logger.info("Retrieved template from database");
        return template;
    }

    private RQMDocument retrieveDocumentFromDatabase(Long documentId) {
        if (documentId == null) {
            return null;
        }
        logger.info("Getting document from database");
        RQMDocument document = null;
        logger.info("Retrieved document from database");
        return document;
    }

    private List<RQMElement> retrieveElementsFromDatabase(Long documentId) {
        logger.info("Getting elements from database");
        List<RQMElement> elements = null;
        logger.info("Retrieved elements from database");
        return elements;
    }
}

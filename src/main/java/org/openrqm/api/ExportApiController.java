/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.openrqm.exporting.Exporter;
import org.openrqm.exporting.MarkdownExporter;
import org.openrqm.exporting.PdfExporter;
import org.openrqm.exporting.RQMExporter;
import org.openrqm.mapper.DocumentRowMapper;
import org.openrqm.mapper.ElementRowMapper;
import org.openrqm.mapper.TemplateRowMapper;
import org.openrqm.model.RQMDocument;
import org.openrqm.model.RQMElement;
import org.openrqm.model.RQMTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExportApiController implements ExportApi {

    private static final Logger logger = LoggerFactory.getLogger(ExportApiController.class);

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public ExportApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.ofNullable(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Resource> exportMarkdown(
            @NotNull @ApiParam(value = "The document to export", required = true) @Valid @RequestParam(value = "documentId", required = true) Long documentId,
            @NotNull @ApiParam(value = "The template to use for the export", required = true) @Valid @RequestParam(value = "templateId", required = true) Long templateId) {
        return export(new MarkdownExporter(), documentId, templateId);
    }

    @Override
    public ResponseEntity<Resource> exportPdf(
            @NotNull @ApiParam(value = "The document to export", required = true) @Valid @RequestParam(value = "documentId", required = true) Long documentId,
            @NotNull @ApiParam(value = "The template to use for the export", required = true) @Valid @RequestParam(value = "templateId", required = true) Long templateId) {
        return export(new PdfExporter(), documentId, templateId);
    }
    
    @Override
    public ResponseEntity<Resource> exportRaw(
            @NotNull @ApiParam(value = "The document to export", required = true) @Valid @RequestParam(value = "documentId", required = true) Long documentId) {
        return export(new RQMExporter(), documentId, null);
    }

    @Override
    public ResponseEntity<List<RQMTemplate>> getMarkdownTemplates() {
        try {
            List<RQMTemplate> templates = jdbcTemplate.query("SELECT * FROM export_template WHERE type = 'markdown';", new TemplateRowMapper());
            return new ResponseEntity<>(templates, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<RQMTemplate>> getPdfTemplates() {
        try {
            List<RQMTemplate> templates = jdbcTemplate.query("SELECT * FROM export_template WHERE type = 'pdf';", new TemplateRowMapper());
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
        RQMTemplate template = jdbcTemplate.queryForObject("SELECT * FROM export_template WHERE id = ?;", new Object[]{ templateId }, new TemplateRowMapper());
        logger.info("Retrieved template from database");
        return template;
    }

    private RQMDocument retrieveDocumentFromDatabase(Long documentId) {
        if (documentId == null) {
            return null;
        }
        logger.info("Getting document from database");
        RQMDocument document = jdbcTemplate.queryForObject("SELECT * FROM document WHERE id = ?;", new Object[]{ documentId }, new DocumentRowMapper());
        logger.info("Retrieved document from database");
        return document;
    }

    private List<RQMElement> retrieveElementsFromDatabase(Long documentId) {
        logger.info("Getting elements from database");
        List<RQMElement> elements = jdbcTemplate.query("SELECT * FROM element WHERE document_id = ? ORDER BY rank;", new Object[] { documentId } , new ElementRowMapper());
        logger.info("Retrieved elements from database");
        return elements;
    }
}

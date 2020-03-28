/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.openrqm.mapper.DocumentRowMapper;
import org.openrqm.mapper.LinkRowMapper;
import org.openrqm.mapper.ThemeRowMapper;
import org.openrqm.model.RQMDocument;
import org.openrqm.model.RQMLink;
import org.openrqm.model.RQMTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DocumentApiController implements DocumentApi {

    private static final Logger logger = LoggerFactory.getLogger(DocumentApiController.class);

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public DocumentApiController(ObjectMapper objectMapper, HttpServletRequest request) {
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
    public ResponseEntity<Void> deleteDocument(@NotNull @ApiParam(value = "The document to delete", required = true) @Valid @RequestParam(value = "documentId", required = true) Long documentId) {
        try {
            jdbcTemplate.update("DELETE FROM document WHERE id = ?;", documentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RQMDocument> getDocument(@NotNull @ApiParam(value = "The element above", required = true) @Valid @RequestParam(value = "documentId", required = true) Long documentId) {
        try {
            RQMDocument document = jdbcTemplate.queryForObject("SELECT * FROM document WHERE id = ?;", new Object[]{ documentId }, new DocumentRowMapper());
            return new ResponseEntity<>(document, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public ResponseEntity<List<RQMLink>> getLinksOfDocument(@NotNull @ApiParam(value = "The document id to identify the correct links", required = true) @Valid @RequestParam(value = "documentId", required = true) Long documentId) {
        try {
            List<RQMLink> linksDetails = jdbcTemplate.query(
                    "SELECT l.id, l.from_element_id, l.from_document_id, l.to_element_id, l.to_document_id, d.short_name, l.link_type_id " +
                    "FROM link l " + 
                    "JOIN document d " + 
                    "ON d.id = l.to_document_id " + 
                    "WHERE l.from_document_id = ? OR l.to_document_id = ?;",
                    new Object[] { documentId, documentId } , new LinkRowMapper());
            return new ResponseEntity<>(linksDetails, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<RQMTheme>> getThemesOfDocument(@NotNull @ApiParam(value = "The document id to identify the correct themes", required = true) @Valid @RequestParam(value = "documentId", required = true) Long documentId) {
        try {
            List<RQMTheme> themes = jdbcTemplate.query("SELECT * FROM theme JOIN document_theme ON id = document_id WHERE document_id = ?;", new Object[] { documentId } , new ThemeRowMapper());
            return new ResponseEntity<>(themes, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> patchDocument(@ApiParam(value = "The document to update", required = true) @Valid @RequestBody RQMDocument document) {
        //TODO: check that the time is set server-side in UTC; Timestamp.from() might make problems for 2k38
        Instant currentInstant = Instant.now();
        //TODO: set last_modified_by_id from session
        long author_id = 1;
        try {
            jdbcTemplate.update("UPDATE document SET workspace_id = ?, external_identifier = ?, name = ?, short_name = ?, "
                    + "description = ?, confidentiality = ?, author_id = ?, language_id = ?, approver_id = ?, reviewer_text = ?, "
                    + "last_modified_by_id = ?, last_modified_on = ?, baseline_major = ?, baseline_minor = ?, baseline_review = ?, "
                    + "previous_baseline_id = ? WHERE id = ?;",
                    document.getWorkspaceId(), document.getExternalIdentifier(), document.getName(),
                    document.getShortName(), document.getDescription(), document.getConfidentiality(), author_id, document.getLanguageId(),
                    document.getApproverId(), document.getReviewerText(), author_id, Timestamp.from(currentInstant),
                    document.getBaselineMajor(), document.getBaselineMinor(), document.getBaselineReview(), document.getPreviousBaselineId(),
                    document.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> postDocument(@ApiParam(value = "The document to create", required=true) @Valid @RequestBody RQMDocument document) {
        try {
            //TODO: check that the time is set server-side in UTC; Timestamp.from() might make problems for 2k38
            Instant currentInstant = Instant.now();
            //TODO: load author and last_modified_by_id from session
            long author_id = 1;
            //TODO: set internal identifier in the server, with persistent incremented id
            long internal_identifier = 123;
            jdbcTemplate.update("INSERT INTO document(id, workspace_id, internal_identifier, external_identifier, name, short_name, description, "
                    + "confidentiality, author_id, language_id, approver_id, reviewer_text, last_modified_by_id, last_modified_on, baseline_major, "
                    + "baseline_minor, baseline_review, previous_baseline_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                    0, document.getWorkspaceId(), internal_identifier, document.getExternalIdentifier(), document.getName(),
                    document.getShortName(), document.getDescription(), document.getConfidentiality(), author_id, document.getLanguageId(),
                    document.getApproverId(), document.getReviewerText(), author_id, Timestamp.from(currentInstant),
                    document.getBaselineMajor(), document.getBaselineMinor(), document.getBaselineReview(), document.getPreviousBaselineId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

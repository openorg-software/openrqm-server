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
import java.time.ZoneOffset;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import javax.validation.Valid;
import org.openrqm.mapper.DocumentRowMapper;
import org.openrqm.model.RQMDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;

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
    public ResponseEntity<RQMDocument> getDocument() {
        try {
            Long id = new Long(1);
            RQMDocument document = jdbcTemplate.queryForObject("SELECT * FROM document WHERE id = ?;", new Object[]{id}, new DocumentRowMapper());
            return new ResponseEntity<>(document, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RQMDocument> postDocument(@ApiParam(value = "The document to create") @Valid @RequestBody RQMDocument document) {
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

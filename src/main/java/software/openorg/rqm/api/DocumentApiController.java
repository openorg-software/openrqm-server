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
import software.openorg.rqm.model.RQMDocument;
import software.openorg.rqm.model.RQMLink;
import software.openorg.rqm.model.RQMTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DocumentApiController implements DocumentApi {

    private static final Logger logger = LoggerFactory.getLogger(DocumentApiController.class);
    
    @Override
    public ResponseEntity<Void> deleteDocument(@NotNull @Parameter(name = "documentId", description = "The document to delete", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "documentId", required = true) Long documentId) {
        try {
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RQMDocument> getDocument(@NotNull @Parameter(name = "documentId", description = "The document identifier", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "documentId", required = true) Long documentId) {
        try {
            RQMDocument document = null;
            return new ResponseEntity<>(document, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public ResponseEntity<List<RQMLink>> getLinksOfDocument(@NotNull @Parameter(name = "documentId", description = "The document id to identify the correct links", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "documentId", required = true) Long documentId) {
        try {
            List<RQMLink> linksDetails = null;
            return new ResponseEntity<>(linksDetails, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<RQMTheme>> getThemesOfDocument(@NotNull @Parameter(name = "documentId", description = "The document id to identify the correct themes", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "documentId", required = true) Long documentId) {
        try {
            List<RQMTheme> themes = null;
            return new ResponseEntity<>(themes, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> patchDocument(@Parameter(name = "RQMDocument", description = "The document to update", required = true, schema = @Schema(description = "")) @Valid @RequestBody RQMDocument rqMDocument) {
        //TODO: check that the time is set server-side in UTC; Timestamp.from() might make problems for 2k38
        //TODO: set last_modified_by_id from session
        try {
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> postDocument(@Parameter(name = "RQMDocument", description = "The document to create", required = true, schema = @Schema(description = "")) @Valid @RequestBody RQMDocument rqMDocument) {
        try {
            //TODO: check that the time is set server-side in UTC; Timestamp.from() might make problems for 2k38
            //TODO: load author and last_modified_by_id from session
            //TODO: set internal identifier in the server, with persistent incremented id
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

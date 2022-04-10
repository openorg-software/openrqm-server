/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package software.openorg.rqm.api;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import software.openorg.rqm.model.RQMWorkspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WorkspaceApiController implements WorkspaceApi {

    private static final Logger logger = LoggerFactory.getLogger(WorkspaceApiController.class);
    
    @Override
    public ResponseEntity<Void> deleteWorkspace(@NotNull @Parameter(name = "workspaceId", description = "The workspace to delete", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "workspaceId", required = true) Long workspaceId) {
        try {
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RQMWorkspace> getWorkspace(@NotNull @Parameter(name = "workspaceId", description = "The workspace identifier", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "workspaceId", required = true) Long workspaceId) {
        try {
            RQMWorkspace workspace = null;
            return new ResponseEntity<>(workspace, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> patchWorkspace(@Parameter(name = "RQMWorkspace", description = "The workspace to update", required = true, schema = @Schema(description = "")) @Valid @RequestBody RQMWorkspace rqMWorkspace) {
        try {
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public ResponseEntity<Void> postWorkspace(@Parameter(name = "RQMWorkspace", description = "The workspace to create", required = true, schema = @Schema(description = "")) @Valid @RequestBody RQMWorkspace rqMWorkspace) {
        try {
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

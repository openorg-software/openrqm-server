/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.openrqm.mapper.WorkspaceRowMapper;
import org.openrqm.model.RQMWorkspace;
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
public class WorkspaceApiController implements WorkspaceApi {

    private static final Logger logger = LoggerFactory.getLogger(WorkspaceApiController.class);

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkspaceApiController(ObjectMapper objectMapper, HttpServletRequest request) {
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
    public ResponseEntity<Void> deleteWorkspace(@NotNull @ApiParam(value = "The workspace to delete", required = true) @Valid @RequestParam(value = "workspaceId", required = true) Long workspaceId) {
        try {
            jdbcTemplate.update("DELETE FROM workspace WHERE id = ?;", workspaceId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RQMWorkspace> getWorkspace(@NotNull @ApiParam(value = "The workspace identifier", required = true) @Valid @RequestParam(value = "workspaceId", required = true) Long workspaceId) {
        try {
            RQMWorkspace workspace = jdbcTemplate.queryForObject("SELECT * FROM workspace WHERE id = ?;", new Object[] { workspaceId } , new WorkspaceRowMapper());
            return new ResponseEntity<>(workspace, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> patchWorkspace(@ApiParam(value = "The workspace to update", required=true) @Valid @RequestBody RQMWorkspace workspace) {
        try {
            jdbcTemplate.update("UPDATE workspace SET name = ?, workspace_id = ? WHERE id = ?;",
                    workspace.getName(), workspace.getWorkspaceId(), workspace.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public ResponseEntity<Void> postWorkspace(@ApiParam(value = "The workspace to create", required=true) @Valid @RequestBody RQMWorkspace workspace) {
        try {
            jdbcTemplate.update("INSERT INTO workspace(id, name, workspace_id) VALUES (?, ?, ?);",
                    0, workspace.getName(), workspace.getWorkspaceId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

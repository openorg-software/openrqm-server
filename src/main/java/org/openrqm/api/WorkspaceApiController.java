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
import org.openrqm.mapper.WorkspaceAccessgroupRowMapper;
import org.openrqm.mapper.WorkspaceRowMapper;
import org.openrqm.mapper.WorkspaceUserRowMapper;
import org.openrqm.model.RQMWorkspace;
import org.openrqm.model.RQMWorkspaceAccessgroup;
import org.openrqm.model.RQMWorkspaceAccessgroups;
import org.openrqm.model.RQMWorkspaceUser;
import org.openrqm.model.RQMWorkspaceUsers;
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
    public ResponseEntity<Void> addAccessGroupToWorkspace(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "workspaceId", required = true) Long workspaceId, @ApiParam(value = "") @Valid @RequestBody RQMWorkspaceAccessgroup accessGroup) {
        try {
            jdbcTemplate.update("INSERT INTO workspace_accessgroup(workspace_id, accessgroup_id, permissions) VALUES (?, ?, ?);", workspaceId, accessGroup.getAccessGroupId(), accessGroup.getPermissions());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> addUserToWorkspace(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "workspaceId", required = true) Long workspaceId, @ApiParam(value = "") @Valid @RequestBody RQMWorkspaceUser user) {
        try {
            jdbcTemplate.update("INSERT INTO workspace_user(workspace_id, user_id, permissions) VALUES (?, ?, ?);", workspaceId, user.getUserId(), user.getPermissions());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> deleteAccessGroupOfWorkspace(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "workspaceId", required = true) Long workspaceId, @NotNull @ApiParam(value = "The access group to delete", required = true) @Valid @RequestParam(value = "accessGroupId", required = true) Long accessGroupId) {
        try {
            jdbcTemplate.update("DELETE FROM workspace_accessgroup WHERE workspace_id = ? AND accessgroup_id = ?;", workspaceId, accessGroupId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> deleteUserOfWorkspace(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "workspaceId", required = true) Long workspaceId, @NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "userId", required = true) Long userId) {
        try {
            jdbcTemplate.update("DELETE FROM workspace_user WHERE workspace_id = ? AND user_id = ?;", workspaceId, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public ResponseEntity<RQMWorkspaceAccessgroups> getAccessGroupsOfWorkspace(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "workspaceId", required = true) Long workspaceId) {
        try {
            List<RQMWorkspaceAccessgroup> workspaceAccessgroupList = jdbcTemplate.query("SELECT * FROM workspace_accessgroup WHERE workspace_id = ?;", new Object[] { workspaceId } , new WorkspaceAccessgroupRowMapper());
            RQMWorkspaceAccessgroups workspaceAccessgroups = new RQMWorkspaceAccessgroups();
            workspaceAccessgroups.addAll(workspaceAccessgroupList); //TODO: improve this, we are touching elements twice here
            return new ResponseEntity<>(workspaceAccessgroups, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RQMWorkspaceUsers> getUsersOfWorkspace(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "workspaceId", required = true) Long workspaceId) {
        try {
            List<RQMWorkspaceUser> workspaceUserList = jdbcTemplate.query("SELECT * FROM workspace_user WHERE workspace_id = ?;", new Object[] { workspaceId } , new WorkspaceUserRowMapper());
            RQMWorkspaceUsers workspaceUsers = new RQMWorkspaceUsers();
            workspaceUsers.addAll(workspaceUserList); //TODO: improve this, we are touching elements twice here
            return new ResponseEntity<>(workspaceUsers, HttpStatus.OK);
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
    public ResponseEntity<Void> patchAccessGroupOfWorkspace(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "workspaceId", required = true) Long workspaceId, @ApiParam(value = "") @Valid @RequestBody RQMWorkspaceAccessgroup accessGroup) {
        try {
            jdbcTemplate.update("UPDATE workspace_accessgroup SET permissions = ? WHERE workspace_id = ? AND accessgroup_id = ?;", accessGroup.getPermissions(), workspaceId, accessGroup.getAccessGroupId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> patchUserOfWorkspace(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "workspaceId", required = true) Long workspaceId,@ApiParam(value = "") @Valid @RequestBody RQMWorkspaceUser user) {
        try {
            jdbcTemplate.update("UPDATE workspace_user SET permissions = ? WHERE workspace_id = ? AND user_id = ?;", user.getPermissions(), workspaceId, user.getUserId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> patchWorkspace(@ApiParam(value = "The workspace to update", required = true) @Valid @RequestBody RQMWorkspace workspace) {
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
    public ResponseEntity<Void> postWorkspace(@ApiParam(value = "The workspace to create", required = true) @Valid @RequestBody RQMWorkspace workspace) {
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

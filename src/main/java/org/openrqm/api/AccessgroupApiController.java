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
import org.openrqm.mapper.AccessGroupRowMapper;
import org.openrqm.mapper.AccessGroupUserRowMapper;
import org.openrqm.model.RQMAccessGroup;
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
public class AccessgroupApiController implements AccessgroupApi {

    private static final Logger logger = LoggerFactory.getLogger(AccessgroupApiController.class);

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public AccessgroupApiController(ObjectMapper objectMapper, HttpServletRequest request) {
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
    public ResponseEntity<Void> addAccessgroup(@ApiParam(value = "The access group to create", required = true) @Valid @RequestBody RQMAccessGroup accessGroup) {
        try {
            jdbcTemplate.update("INSERT INTO accessgroup(id, name) VALUES (?, ?);", 0, accessGroup.getName());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> addUserToAccessGroup(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "accessGroupId", required = true) Long accessGroupId, @NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "userId", required = true) Long userId) {
        try {
            jdbcTemplate.update("INSERT INTO accessgroup_user(accessgroup_id, user_id) VALUES (?, ?);", accessGroupId, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> deleteAccessgroup(@NotNull @ApiParam(value = "The access group to delete", required = true) @Valid @RequestParam(value = "accessGroupId", required = true) Long accessGroupId) {
        try {
            jdbcTemplate.update("DELETE FROM accessgroup WHERE id = ?;", accessGroupId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> deleteUserOfAccessGroup(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "accessGroupId", required = true) Long accessGroupId, @NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "userId", required = true) Long userId) {
        try {
            jdbcTemplate.update("DELETE FROM accessgroup_user WHERE accessgroup_id = ? AND user_id = ?;", accessGroupId, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RQMAccessGroup> getAccessgroup(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "accessGroupId", required = true) Long accessGroupId) {
        try {
            RQMAccessGroup accessGroup = jdbcTemplate.queryForObject("SELECT * FROM accessgroup WHERE id = ?;", new Object[]{ accessGroupId }, new AccessGroupRowMapper());
            return new ResponseEntity<>(accessGroup, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Long>> getUsersOfAccessGroup(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "accessGroupId", required = true) Long accessGroupId) {
        try {
            List<Long> accessGroupUserList = jdbcTemplate.query("SELECT user_id FROM accessgroup_user WHERE accessgroup_id = ?;", new Object[] { accessGroupId } , new AccessGroupUserRowMapper());
            return new ResponseEntity<>(accessGroupUserList, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> patchAccessgroup(@ApiParam(value = "The access group to update", required = true) @Valid @RequestBody RQMAccessGroup accessGroup) {
        try {
            jdbcTemplate.update("UPDATE accessgroup SET name = ? WHERE id = ?;", accessGroup.getId(), accessGroup.getName());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

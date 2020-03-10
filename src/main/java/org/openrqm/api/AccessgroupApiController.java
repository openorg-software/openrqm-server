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
import org.openrqm.model.RQMAccessGroup;
import org.openrqm.model.RQMAccessGroupUser;
import org.openrqm.model.RQMAccessGroupUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> addUserToAccessGroup(@ApiParam(value = "") @Valid @RequestBody RQMAccessGroupUser user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> deleteAccessgroup(@NotNull @ApiParam(value = "The access group to delete", required = true) @Valid @RequestParam(value = "accessGroupId", required = true) Long accessGroupId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> deleteUserOfAccessGroup(@ApiParam(value = "") @Valid @RequestBody RQMAccessGroupUser user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<RQMAccessGroup> getAccessgroup() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<RQMAccessGroupUsers> getUsersOfAccessGroup() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> patchAccessgroup(@ApiParam(value = "The access group to update", required = true) @Valid @RequestBody RQMAccessGroup accessGroup) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> patchUserOfAccessGroup(@ApiParam(value = "") @Valid @RequestBody RQMAccessGroupUser user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}

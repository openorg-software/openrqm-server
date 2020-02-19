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
import org.openrqm.model.RQMLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LinkApiController implements LinkApi {

    private static final Logger logger = LoggerFactory.getLogger(LinkApiController.class);

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public LinkApiController(ObjectMapper objectMapper, HttpServletRequest request) {
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
    public ResponseEntity<RQMLink> linkElement(@NotNull @ApiParam(value = "The element that is the source of the link", required = true) @Valid @RequestParam(value = "fromElementId", required = true) Long fromElementId, @NotNull @ApiParam(value = "The element that is the target of the link", required = true) @Valid @RequestParam(value = "toElementId", required = true) Long toElementId, @NotNull @ApiParam(value = "The type of the link", required = true) @Valid @RequestParam(value = "linkTypeId", required = true) Long linkTypeId) {
        try {
            jdbcTemplate.update("INSERT INTO link(id, from_element_id, to_element_id, link_type_id) VALUES (?, ?, ?, ?);",
                    0, fromElementId, toElementId, linkTypeId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

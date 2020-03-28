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
import org.openrqm.mapper.ElementTypeColorRowMapper;
import org.openrqm.mapper.ThemeRowMapper;
import org.openrqm.model.RQMElementTypeColor;
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
public class ThemeApiController implements ThemeApi {

    private static final Logger logger = LoggerFactory.getLogger(ThemeApiController.class);

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public ThemeApiController(ObjectMapper objectMapper, HttpServletRequest request) {
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
    public ResponseEntity<Void> createTheme(@ApiParam(value = "", required = true) @Valid @RequestBody RQMTheme theme) {
        try {
            jdbcTemplate.update("INSERT INTO theme(id, link_from_color, link_to_color) VALUES (?, ?, ?);", 0, theme.getLinkFromHighlightColor(), theme.getLinkToHighlightColor());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<RQMElementTypeColor>> getElementTypeColorsOfTheme(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "themeId", required = true) Long themeId) {
        try {
            List<RQMElementTypeColor> elementTypeColorsList = jdbcTemplate.query("SELECT * FROM theme_element_type WHERE theme_id = ?;", new Object[]{ themeId }, new ElementTypeColorRowMapper());
            return new ResponseEntity<>(elementTypeColorsList, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RQMTheme> getTheme(@NotNull @ApiParam(value = "The theme id", required = true) @Valid @RequestParam(value = "themeId", required = true) Long themeId) {
        try {
            RQMTheme theme = jdbcTemplate.queryForObject("SELECT * FROM theme WHERE id = ?;", new Object[]{ themeId }, new ThemeRowMapper());
            return new ResponseEntity<>(theme, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> updateTheme(@ApiParam(value = "", required = true) @Valid @RequestBody RQMTheme theme) {
        try {
            jdbcTemplate.update("UPDATE theme SET link_from_color = ?, link_to_color = ? WHERE id = ?;", theme.getLinkFromHighlightColor(), theme.getLinkToHighlightColor(), theme.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

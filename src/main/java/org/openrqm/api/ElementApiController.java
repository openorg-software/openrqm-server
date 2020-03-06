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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Cleaner;
import org.jsoup.select.Elements;
import org.openrqm.model.RQMElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import utils.EditorContentWhitelist;
import utils.RankUtils;

@Controller
public class ElementApiController implements ElementApi {

    private static final Logger logger = LoggerFactory.getLogger(ElementApiController.class);
    private static final RankUtils rankUtils = new RankUtils();

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public ElementApiController(ObjectMapper objectMapper, HttpServletRequest request) {
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
    public ResponseEntity<Void> deleteElement(@NotNull @ApiParam(value = "The element to delete", required = true) @Valid @RequestParam(value = "elementId", required = true) Long elementId) {
        try {
            jdbcTemplate.update("DELETE FROM element WHERE id = ?;", elementId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> patchElement(@ApiParam(value = "The element to update", required=true) @Valid @RequestBody RQMElement element) {
        try {
            String processedContent = sanitizeAndProcessElementContent(element.getContent(), element.getId());
            jdbcTemplate.update("UPDATE element SET element_type_id = ?, content = ?, rank = ?, parent_element_id = ? WHERE id = ?;",
                    element.getElementTypeId(), processedContent, element.getRank(), element.getParentElementId(), element.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> postElement(@ApiParam(value = "The element to create, if the element should be created at the highest level, the parentElementId shall be null", required=true) @Valid @RequestBody RQMElement element, @NotNull @ApiParam(value = "The rank of the element above, if no above element exists this shall be set to aaaaaaaaaaaaaaaaaaaa", required = true) @Valid @RequestParam(value = "aboveRank", required = true) String aboveRank, @NotNull @ApiParam(value = "The rank of the element below, if not above element exists this shall be set to an empty string", required = true) @Valid @RequestParam(value = "belowRank", required = true) String belowRank) {
        String newRank;
        if (belowRank.isEmpty()) {
            newRank = rankUtils.calculateNewRank(aboveRank, RankUtils.NEW_ELEMENTS, RankUtils.MAX_ELEMENTS);
            if (newRank == null) {
                logger.error("Maximal rank reached, no more elements can be inserted.");
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            newRank = rankUtils.calculateMiddleRank(aboveRank, belowRank, RankUtils.MAX_LENGTH);
            if (newRank == null) {
                //TODO: trigger re-rank
                logger.error("Maximal length of rank reached");
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        try {
            String processedContent = sanitizeAndProcessElementContent(element.getContent(), element.getId());
            jdbcTemplate.update("INSERT INTO element(id, document_id, element_type_id, content, rank, parent_element_id) VALUES (?, ?, ?, ?, ?, ?);",
                    0, element.getDocumentId(), element.getElementTypeId(), processedContent, newRank, element.getParentElementId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String sanitizeAndProcessElementContent(String elementContent, Long elementId) {
        Document htmlContent = Jsoup.parseBodyFragment(elementContent);
        Document cleanedHtmlContent = new Cleaner(EditorContentWhitelist.allowedEditorContent()).clean(htmlContent);
        Elements images = cleanedHtmlContent.select("img");
        if (images.size() != 0) {
            logger.info("There are images in this element, that need to be stored separately");
        }
        return cleanedHtmlContent.toString();
    }
}

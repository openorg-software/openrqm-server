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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.safety.Cleaner;
import software.openorg.rqm.model.RQMElement;
import software.openorg.rqm.model.RQMLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import utils.EditorContentSafelist;
import utils.RankUtils;

@Controller
public class ElementApiController implements ElementApi {

    private static final Logger logger = LoggerFactory.getLogger(ElementApiController.class);
    private static final RankUtils rankUtils = new RankUtils();

    @Override
    public ResponseEntity<Void> deleteElement(@NotNull @Parameter(name = "elementId", description = "The element to delete", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "elementId", required = true) Long elementId) {
        try {
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public ResponseEntity<List<RQMLink>> getIncomingLinksOfElement(@NotNull @Parameter(name = "elementId", description = "The id of the element", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "elementId", required = true) Long elementId) {
        try {
            List<RQMLink> links = null;
            return new ResponseEntity<>(links, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<RQMLink>> getOutgoingLinksOfElement(@NotNull @Parameter(name = "elementId", description = "The id of the element", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "elementId", required = true) Long elementId) {
        try {
            List<RQMLink> links = null;
            return new ResponseEntity<>(links, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> patchElement(@Parameter(name = "RQMElement", description = "The element to update", required = true, schema = @Schema(description = "")) @Valid @RequestBody RQMElement rqMElement) {
        try {
            String processedContent = sanitizeAndProcessElementContent(rqMElement.getContent());
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> postElement(@NotNull @Parameter(name = "aboveRank", description = "The rank of the element above, if no above element exists this shall be set to aaaaaaaaaaaaaaaaaaaa", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "aboveRank", required = true) String aboveRank, @NotNull @Parameter(name = "belowRank", description = "The rank of the element below, if no below element exists this shall be set to an empty string", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "belowRank", required = true) String belowRank, @Parameter(name = "RQMElement", description = "The element to create, if the element should be created at the highest level, the parentElementId shall be null", required = true, schema = @Schema(description = "")) @Valid @RequestBody RQMElement rqMElement) {
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
            String processedContent = sanitizeAndProcessElementContent(rqMElement.getContent());
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String sanitizeAndProcessElementContent(String elementContent) {
        Document htmlContent = Jsoup.parseBodyFragment(elementContent);
        Document cleanedHtmlContent = new Cleaner(EditorContentSafelist.allowedEditorContent()).clean(htmlContent);
        OutputSettings outputSettings = htmlContent.outputSettings();
        outputSettings.prettyPrint(false).charset("UTF-8");
        cleanedHtmlContent = cleanedHtmlContent.outputSettings(outputSettings);
        String result = cleanedHtmlContent.body().html();
        return result;
    }
}

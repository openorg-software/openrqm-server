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
import software.openorg.rqm.model.RQMLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LinkApiController implements LinkApi {

    private static final Logger logger = LoggerFactory.getLogger(LinkApiController.class);

    @Override
    public ResponseEntity<Void> deleteLink(@NotNull @Parameter(name = "linkId", description = "The id of the link", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "linkId", required = true) Long linkId) {
        try {
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RQMLink> linkElement(@Parameter(name = "RQMLink", description = "", schema = @Schema(description = "")) @Valid @RequestBody(required = false) RQMLink rqMLink) {
        try {
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

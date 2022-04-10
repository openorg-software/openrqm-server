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
import software.openorg.rqm.model.RQMElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ElementsApiController implements ElementsApi {

    private static final Logger logger = LoggerFactory.getLogger(ElementsApiController.class);

    @Override
    public ResponseEntity<List<RQMElement>> getElements(@NotNull @Parameter(name = "documentId", description = "The document id for which the elements are fetched", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "documentId", required = true) Long documentId) {
        try {
            List<RQMElement> elements = null;
            return new ResponseEntity<>(elements, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

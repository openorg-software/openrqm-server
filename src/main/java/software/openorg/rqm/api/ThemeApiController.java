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
import software.openorg.rqm.model.RQMElementTypeColor;
import software.openorg.rqm.model.RQMTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThemeApiController implements ThemeApi {

    private static final Logger logger = LoggerFactory.getLogger(ThemeApiController.class);

    @Override
    public ResponseEntity<Void> createTheme(@Parameter(name = "RQMTheme", description = "", required = true, schema = @Schema(description = "")) @Valid @RequestBody RQMTheme rqMTheme) {
        try {
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<RQMElementTypeColor>> getElementTypeColorsOfTheme(@NotNull @Parameter(name = "themeId", description = "", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "themeId", required = true) Long themeId) {
        try {
            List<RQMElementTypeColor> elementTypeColorsList = null;
            return new ResponseEntity<>(elementTypeColorsList, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RQMTheme> getTheme(@NotNull @Parameter(name = "themeId", description = "The theme id", required = true, schema = @Schema(description = "")) @Valid @RequestParam(value = "themeId", required = true) Long themeId) {
        try {
            RQMTheme theme = null;
            return new ResponseEntity<>(theme, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> updateTheme(@Parameter(name = "RQMTheme", description = "", required = true, schema = @Schema(description = "")) @Valid @RequestBody RQMTheme rqMTheme) {
        try {
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

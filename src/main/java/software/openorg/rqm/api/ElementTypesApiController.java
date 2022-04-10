/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package software.openorg.rqm.api;

import java.util.List;
import org.springframework.stereotype.Controller;
import software.openorg.rqm.model.RQMElementType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
public class ElementTypesApiController implements ElementTypesApi {
    
    private static final Logger logger = LoggerFactory.getLogger(ElementTypesApiController.class);

    @Override
    public ResponseEntity<List<RQMElementType>> getElementTypes() {
        try {
            List<RQMElementType> elementTypes = null;
            return new ResponseEntity<>(elementTypes, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}



    


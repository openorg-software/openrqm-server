package org.openrqm.controller;

import org.openrqm.model.RQMElements;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.openrqm.api.ElementsApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.openrqm.mapper.ElementRowMapper;
import org.openrqm.model.RQMElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

@Controller
public class ElementsApiController implements ElementsApi {

    private static final Logger logger = LoggerFactory.getLogger(ElementsApiController.class);

    private final HttpServletRequest request;

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    public ElementsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<RQMElements> elementsGet(
            @ApiParam(value = "The document id for which the elements are fetched") @Valid @RequestParam(value = "documentid", required = false) Long documentid) {
        try {
            List<RQMElement> elementsList = jdbcTemplate.query("SELECT * FROM element WHERE document_id = ?", new Object[] { documentid } , new ElementRowMapper());
            RQMElements elements = new RQMElements();
            elements.addAll(elementsList); //TODO: improve this, we are touching elements twice here
            return new ResponseEntity<>(elements, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

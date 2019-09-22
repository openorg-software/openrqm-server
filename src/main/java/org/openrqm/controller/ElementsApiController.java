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
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

@Controller
public class ElementsApiController implements ElementsApi {

    private static final Logger logger = LoggerFactory.getLogger(ElementsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public ElementsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<RQMElements> elementsGet(
            @ApiParam(value = "The document id for which the elements are fetched") @Valid @RequestParam(value = "documentid", required = false) Long documentid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<>(objectMapper.readValue("\"\"", RQMElements.class),
                        HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                logger.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}

package org.openrqm.controller;

import org.openrqm.model.RQMElement;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.openrqm.api.ElementApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

@Controller
public class ElementApiController implements ElementApi {

    private static final Logger logger = LoggerFactory.getLogger(ElementApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public ElementApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<Void> elementPatch(
            @ApiParam(value = "The element to update") @Valid @RequestBody RQMElement element) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> elementPost(
            @ApiParam(value = "The element to create") @Valid @RequestBody RQMElement element) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> elementPut(
            @ApiParam(value = "The element to update") @Valid @RequestBody RQMElement element) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}

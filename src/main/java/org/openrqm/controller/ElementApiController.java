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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

@CrossOrigin
@Controller
public class ElementApiController implements ElementApi {

    private static final Logger log = LoggerFactory.getLogger(ElementApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ElementApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> elementPatch(
            @ApiParam(value = "The element to update") @Valid @RequestBody RQMElement element) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> elementPost(
            @ApiParam(value = "The element to create") @Valid @RequestBody RQMElement element) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> elementPut(
            @ApiParam(value = "The element to update") @Valid @RequestBody RQMElement element) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}

package org.openrqm.controller;

import org.openrqm.model.RQMDocument;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openrqm.api.DocumentApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

@CrossOrigin
@Controller
public class DocumentApiController implements DocumentApi {

    private static final Logger log = LoggerFactory.getLogger(DocumentApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public DocumentApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<RQMDocument> documentGet() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<RQMDocument>(objectMapper.readValue(
                        "{  \"baseline_review\" : 4,  \"external_identifier\" : 5,  \"last_modified_on\" : \"2000-01-23T04:56:07.000+00:00\",  \"approver_id\" : 7,  \"confidentiality\" : \"confidentiality\",  \"description\" : \"description\",  \"previous_baseline_id\" : 7,  \"language_id\" : 2,  \"workspace_id\" : 6,  \"reviewer_text\" : \"reviewer_text\",  \"last_modified_by_id\" : 9,  \"name\" : \"name\",  \"short_name\" : \"short_name\",  \"id\" : 0,  \"author_id\" : 5,  \"internal_identifier\" : 1,  \"baseline_major\" : 3,  \"baseline_minor\" : 2}",
                        RQMDocument.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<RQMDocument>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<RQMDocument>(HttpStatus.NOT_IMPLEMENTED);
    }

}

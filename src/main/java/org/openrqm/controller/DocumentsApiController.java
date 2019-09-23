package org.openrqm.controller;

import org.openrqm.model.RQMDocuments;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openrqm.api.DocumentsApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import org.openrqm.mapper.DocumentRowMapper;
import org.openrqm.mapper.ElementRowMapper;
import org.openrqm.model.RQMDocument;
import org.openrqm.model.RQMElement;
import org.openrqm.model.RQMElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

@Controller
public class DocumentsApiController implements DocumentsApi {

    private static final Logger logger = LoggerFactory.getLogger(DocumentsApiController.class);

    private final HttpServletRequest request;
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public DocumentsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<RQMDocuments> documentsGet() {
        try {
            List<RQMDocument> documentsList = jdbcTemplate.query("SELECT * FROM document", new DocumentRowMapper());
            RQMDocuments documents = new RQMDocuments();
            documents.addAll(documentsList); //TODO: improve this, we are touching elements twice here
            return new ResponseEntity<>(documents, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package org.openrqm.controller;

import org.openrqm.model.RQMDocument;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openrqm.api.DocumentApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import org.openrqm.mapper.DocumentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

@Controller
public class DocumentApiController implements DocumentApi {

    private static final Logger logger = LoggerFactory.getLogger(DocumentApiController.class);

    private final HttpServletRequest request;
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public DocumentApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<RQMDocument> documentGet() {
        try {
            Long id = new Long(1);
            RQMDocument document = jdbcTemplate.queryForObject("SELECT * FROM document WHERE id = ?", new Object[] { id } , new DocumentRowMapper());
            return new ResponseEntity<>(document, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

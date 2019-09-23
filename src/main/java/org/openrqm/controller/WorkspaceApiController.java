package org.openrqm.controller;

import org.openrqm.model.RQMWorkspace;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openrqm.api.WorkspaceApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

import org.openrqm.mapper.WorkspaceRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

@Controller
public class WorkspaceApiController implements WorkspaceApi {

    private static final Logger logger = LoggerFactory.getLogger(WorkspaceApiController.class);

    private final HttpServletRequest request;
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkspaceApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<RQMWorkspace> getWorkspace() {
        try {
            Long id = new Long(1);
            RQMWorkspace workspace = jdbcTemplate.queryForObject("SELECT * FROM workspace WHERE id = ?", new Object[] { id } , new WorkspaceRowMapper());
            return new ResponseEntity<>(workspace, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

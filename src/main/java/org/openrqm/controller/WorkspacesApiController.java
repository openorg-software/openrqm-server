package org.openrqm.controller;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

import org.openrqm.api.WorkspacesApi;
import org.openrqm.mapper.WorkspaceRowMapper;
import org.openrqm.model.RQMWorkspace;
import org.openrqm.model.RQMWorkspaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

@Controller
public class WorkspacesApiController implements WorkspacesApi {

    private static final Logger logger = LoggerFactory.getLogger(WorkspacesApiController.class);

    private final HttpServletRequest request;
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkspacesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<RQMWorkspaces> workspacesGet() {
        try {
            List<RQMWorkspace> workspacesList = jdbcTemplate.query("SELECT * FROM workspace", new WorkspaceRowMapper());
            RQMWorkspaces workspaces = new RQMWorkspaces();
            workspaces.addAll(workspacesList); //TODO: improve this, we are touching elements twice here
            return new ResponseEntity<>(workspaces, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

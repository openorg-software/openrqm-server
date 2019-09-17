package org.openrqm.controller;

import org.openrqm.model.RQMWorkspace;
import org.openrqm.model.RQMWorkspaces;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openrqm.api.WorkspacesApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

@Controller
public class WorkspacesApiController implements WorkspacesApi {

    private static final Logger logger = LoggerFactory.getLogger(WorkspacesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public WorkspacesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<RQMWorkspaces> workspacesGet() {
        RQMWorkspaces workspaces = new RQMWorkspaces();
        RQMWorkspace current_workspace = new RQMWorkspace();

        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost/openrqm?user=openrqm");
        } catch (SQLException e) {
            logger.error("Could not connect to database: " + e.getMessage());
            return null;
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from workspace");
            while (resultSet.next()) {
                current_workspace.setId(resultSet.getLong("id"));
                current_workspace.setName(resultSet.getString("name"));
                Long workspace_id = resultSet.getLong("workspace_id");
                current_workspace.setWorkspaceId(resultSet.wasNull() ? null : workspace_id);
                logger.info("id: " + resultSet.getInt("id") + ", " + "name: " + resultSet.getString("name") + " "
                        + resultSet.getString("workspace_id"));
                workspaces.add(current_workspace);
                current_workspace = new RQMWorkspace();
            }
        } catch (SQLException e) {
            logger.error("A SQL exception occured: " + e.getMessage());
        }

        return new ResponseEntity<RQMWorkspaces>(workspaces, HttpStatus.OK);
    }

}

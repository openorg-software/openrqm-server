package org.openrqm.controller;

import org.openrqm.model.RQMWorkspace;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openrqm.api.WorkspaceApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

@CrossOrigin
@Controller
public class WorkspaceApiController implements WorkspaceApi {

    private static final Logger logger = LoggerFactory.getLogger(WorkspaceApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public WorkspaceApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<RQMWorkspace> getWorkspace() {
        RQMWorkspace workspace = new RQMWorkspace();

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
            if (resultSet.next()) {
                workspace.setId(resultSet.getLong("id"));
                workspace.setName(resultSet.getString("name"));
                Long workspace_id = resultSet.getLong("workspace_id");
                workspace.setWorkspaceId(resultSet.wasNull() ? null : workspace_id);
                logger.info("id: " + resultSet.getInt("id") + ", " + "name: " + resultSet.getString("name") + " "
                        + resultSet.getString("workspace_id"));
            }
        } catch (SQLException e) {
            logger.error("A SQL exception occured: " + e.getMessage());
        }

        return new ResponseEntity<RQMWorkspace>(workspace, HttpStatus.OK);
    }

}

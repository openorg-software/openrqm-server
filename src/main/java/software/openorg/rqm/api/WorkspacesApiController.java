/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package software.openorg.rqm.api;

import java.util.List;
import org.springframework.stereotype.Controller;
import software.openorg.rqm.model.RQMDocument;
import software.openorg.rqm.model.RQMWorkspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
public class WorkspacesApiController implements WorkspacesApi {

    private static final Logger logger = LoggerFactory.getLogger(WorkspacesApiController.class);

    @Override
    public ResponseEntity<List<RQMWorkspace>> getWorkspaces() {
        try {
            List<RQMWorkspace> workspaces = null;
            getWorkspacesRecursive(workspaces); //TODO: this is a really bad way of doing this, used only for testing
            return new ResponseEntity<>(workspaces, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    private void getWorkspacesRecursive(List<RQMWorkspace> workspaces) {
        for (RQMWorkspace workspace : workspaces) {
            List<RQMDocument> documentsList = null;
            workspace.setDocuments(documentsList);
            List<RQMWorkspace> childWorkspacesList = null;
            getWorkspacesRecursive(childWorkspacesList);
            workspace.setWorkspaces(childWorkspacesList);
        }
    }
}



    


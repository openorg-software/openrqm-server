package org.openrqm.api;

import org.openrqm.model.RQMWorkspaces;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

@Api(value = "workspaces", description = "the workspaces API")
public interface WorkspacesApi {

        @ApiOperation(value = "Get all workspaces", nickname = "workspacesGet", notes = "", response = RQMWorkspaces.class, tags = {
                        "workspaces", })
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "All workspaces were fetched sucessfully", response = RQMWorkspaces.class) })
        @RequestMapping(value = "/workspaces", method = RequestMethod.GET)
        ResponseEntity<RQMWorkspaces> workspacesGet();

}

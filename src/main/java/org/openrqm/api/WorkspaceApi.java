package org.openrqm.api;

import org.openrqm.model.RQMWorkspace;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

@Api(value = "workspace", description = "the workspace API")
public interface WorkspaceApi {

        @ApiOperation(value = "Get a workspace", nickname = "getWorkspace", notes = "", response = RQMWorkspace.class, tags = {
                        "workspace", })
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "The workspace was fetched successfully", response = RQMWorkspace.class) })
        @RequestMapping(value = "/workspace", method = RequestMethod.GET)
        ResponseEntity<RQMWorkspace> getWorkspace();

}

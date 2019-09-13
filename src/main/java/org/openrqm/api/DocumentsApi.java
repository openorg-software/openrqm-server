package org.openrqm.api;

import org.openrqm.model.RQMDocuments;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

@Api(value = "documents", description = "the documents API")
public interface DocumentsApi {

        @ApiOperation(value = "", nickname = "documentsGet", notes = "", response = RQMDocuments.class, tags = {
                        "documents", })
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Documents sucessfully fetche", response = RQMDocuments.class) })
        @RequestMapping(value = "/documents", method = RequestMethod.GET)
        ResponseEntity<RQMDocuments> documentsGet();

}

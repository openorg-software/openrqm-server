package org.openrqm.api;

import org.openrqm.model.RQMDocument;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

@Api(value = "document", description = "the document API")
public interface DocumentApi {

        @ApiOperation(value = "", nickname = "documentGet", notes = "", response = RQMDocument.class, tags = {
                        "document", })
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Document successfully fetched", response = RQMDocument.class) })
        @RequestMapping(value = "/document", method = RequestMethod.GET)
        ResponseEntity<RQMDocument> documentGet();

}

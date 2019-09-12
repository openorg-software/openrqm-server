/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.RQMDocuments;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

@Api(value = "documents", description = "the documents API")
public interface DocumentsApi {

    @ApiOperation(value = "", nickname = "documentsGet", notes = "", response = RQMDocuments.class, tags={ "documents", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Documents sucessfully fetche", response = RQMDocuments.class) })
    @RequestMapping(value = "/documents",
        method = RequestMethod.GET)
    ResponseEntity<RQMDocuments> documentsGet();

}

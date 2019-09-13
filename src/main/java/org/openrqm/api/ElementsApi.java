package org.openrqm.api;

import org.openrqm.model.RQMElements;
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

@Api(value = "elements", description = "the elements API")
public interface ElementsApi {

        @ApiOperation(value = "", nickname = "elementsGet", notes = "", response = RQMElements.class, tags = {
                        "elements", })
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Elements successfully fetched", response = RQMElements.class) })
        @RequestMapping(value = "/elements", method = RequestMethod.GET)
        ResponseEntity<RQMElements> elementsGet(
                        @ApiParam(value = "The document id for which the elements are fetched") @Valid @RequestParam(value = "documentid", required = false) Long documentid);

}

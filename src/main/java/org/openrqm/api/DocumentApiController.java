package org.openrqm.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import javax.validation.Valid;
import org.openrqm.mapper.DocumentRowMapper;
import org.openrqm.model.RQMDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class DocumentApiController implements DocumentApi {

    private static final Logger logger = LoggerFactory.getLogger(DocumentApiController.class);

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public DocumentApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.ofNullable(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<RQMDocument> getDocument() {
        try {
            Long id = new Long(1);
            RQMDocument document = jdbcTemplate.queryForObject("SELECT * FROM document WHERE id = ?;", new Object[]{id}, new DocumentRowMapper());
            return new ResponseEntity<>(document, HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RQMDocument> postDocument(@ApiParam(value = "The document to create") @Valid @RequestBody RQMDocument document) {
        try {
            jdbcTemplate.update("INSERT INTO document(id, workspace_id, internal_identifier, external_identifier, name, short_name, description, "
                    + "confidentiality, author_id, language_id, approver_id, reviewer_text, last_modified_by_id, last_modified_on, baseline_major, "
                    + "baseline_minor, baseline_review, previous_baseline_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                    0, document.getWorkspaceId(), document.getInternalIdentifier(), document.getExternalIdentifier(), document.getName(),
                    document.getShortName(), document.getDescription(), document.getConfidentiality(), document.getAuthorId(), document.getLanguageId(),
                    document.getApproverId(), document.getReviewerText(), document.getLastModifiedById(), document.getLastModifiedOn(),
                    document.getBaselineMajor(), document.getBaselineMinor(), document.getBaselineReview(), document.getPreviousBaselineId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            logger.error(ex.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

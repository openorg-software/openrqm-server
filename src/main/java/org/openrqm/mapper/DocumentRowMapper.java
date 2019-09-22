package org.openrqm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.openrqm.model.RQMDocument;
import org.springframework.jdbc.core.RowMapper;
import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

public class DocumentRowMapper implements RowMapper<RQMDocument> {

    @Override
    public RQMDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        RQMDocument document = new RQMDocument();
        document.setId(rs.getLong("id"));
        document.setWorkspaceId(rs.getLong("workspace_id"));
        document.setInternalIdentifier(rs.getLong("internal_identifier"));
        document.setExternalIdentifier(rs.getLong("external_identifier"));
        document.setName(rs.getString("name"));
        document.setShortName(rs.getString("short_name"));
        document.setDescription(rs.getString("description"));
        document.setConfidentiality(rs.getString("confidentiality"));
        document.setAuthorId(rs.getLong("author_id"));
        document.setLanguageId(rs.getLong("language_id"));
        document.setApproverId(rs.getLong("approver_id"));
        document.setReviewerText(rs.getString("reviewer_text"));
        document.setLastModifiedById(rs.getLong("last_modified_by_id"));
        Timestamp timestamp = rs.getTimestamp("last_modified_on");
        LocalDateTime localDateTime = DateTimeUtils.toLocalDateTime(timestamp);
        document.setLastModifiedOn(OffsetDateTime.of(localDateTime, ZoneOffset.UTC));
        document.setBaselineMajor(rs.getLong("baseline_major"));
        document.setBaselineMinor(rs.getLong("baseline_minor"));
        document.setBaselineReview(rs.getLong("baseline_review"));
        document.setPreviousBaselineId(rs.getLong("prvious_baseline_id"));
        return document;
    }
    
}

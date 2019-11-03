/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.openrqm.model.RQMElement;
import org.springframework.jdbc.core.RowMapper;

public class ElementRowMapper implements RowMapper<RQMElement> {

    @Override
    public RQMElement mapRow(ResultSet rs, int rowNum) throws SQLException {
        RQMElement element = new RQMElement();
        element.setId(rs.getLong("id"));
        element.setDocumentId(rs.getLong("document_id"));
        element.setElementTypeId(rs.getLong("element_type_id"));
        element.setContent(rs.getString("content"));
        element.setRank(rs.getString("rank"));
        element.setParentElementId(rs.getLong("parent_element_id"));
        return element;
    }
    
}

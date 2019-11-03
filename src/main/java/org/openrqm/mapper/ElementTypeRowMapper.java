/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.openrqm.model.RQMElementType;
import org.springframework.jdbc.core.RowMapper;

public class ElementTypeRowMapper implements RowMapper<RQMElementType> {

    @Override
    public RQMElementType mapRow(ResultSet rs, int rowNum) throws SQLException {
        RQMElementType elementType = new RQMElementType();
        elementType.setId(rs.getLong("id"));
        elementType.setName(rs.getString("name"));
        return elementType;
    }
    
}

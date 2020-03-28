/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.openrqm.model.RQMElementTypeColor;
import org.springframework.jdbc.core.RowMapper;

public class ElementTypeColorRowMapper implements RowMapper<RQMElementTypeColor> {

    @Override
    public RQMElementTypeColor mapRow(ResultSet rs, int rowNum) throws SQLException {
        RQMElementTypeColor elementTypeColor = new RQMElementTypeColor();
        elementTypeColor.setThemeId(rs.getLong("theme_id"));
        elementTypeColor.setElementTypeId(rs.getLong("element_type_id"));
        elementTypeColor.setColorCodeHex(rs.getString("color"));
        return elementTypeColor;
    }
    
}

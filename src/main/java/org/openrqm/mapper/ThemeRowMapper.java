/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.openrqm.model.RQMTheme;
import org.springframework.jdbc.core.RowMapper;

public class ThemeRowMapper implements RowMapper<RQMTheme> {

    @Override
    public RQMTheme mapRow(ResultSet rs, int rowNum) throws SQLException {
        RQMTheme theme = new RQMTheme();
        theme.setId(rs.getLong("id"));
        theme.setLinkFromHighlightColor(rs.getString("link_from_color"));
        theme.setLinkToHighlightColor(rs.getString("link_to_color"));
        return theme;
    }
    
}

/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.openrqm.model.RQMTemplate;
import org.springframework.jdbc.core.RowMapper;

public class TemplateRowMapper implements RowMapper<RQMTemplate> {

    @Override
    public RQMTemplate mapRow(ResultSet rs, int rowNum) throws SQLException {
        RQMTemplate template = new RQMTemplate();
        template.setId(rs.getLong("id"));
        template.setName(rs.getString("name"));
        return template;
    }
    
}

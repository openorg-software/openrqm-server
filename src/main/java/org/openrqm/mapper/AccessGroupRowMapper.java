/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.openrqm.model.RQMAccessGroup;
import org.springframework.jdbc.core.RowMapper;

public class AccessGroupRowMapper implements RowMapper<RQMAccessGroup> {

    @Override
    public RQMAccessGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
        RQMAccessGroup accessGroup = new RQMAccessGroup();
        accessGroup.setId(rs.getLong("id"));
        accessGroup.setName(rs.getString("name"));
        return accessGroup;
    }

}
/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.openrqm.model.RQMLinkType;
import org.springframework.jdbc.core.RowMapper;

public class LinkTypeRowMapper implements RowMapper<RQMLinkType> {

    @Override
    public RQMLinkType mapRow(ResultSet rs, int rowNum) throws SQLException {
        RQMLinkType linkType = new RQMLinkType();
        linkType.setId(rs.getLong("id"));
        linkType.setNameFrom(rs.getString("name_from"));
        linkType.setNameTo(rs.getString("name_to"));
        return linkType;
    }
}

/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.openrqm.model.RQMLink;
import org.springframework.jdbc.core.RowMapper;

public class LinkRowMapper implements RowMapper<RQMLink> {

    @Override
    public RQMLink mapRow(ResultSet rs, int rowNum) throws SQLException {
        RQMLink link = new RQMLink();
        link.setId(rs.getLong("id"));
        link.setFromElementId(rs.getLong("from_element_id"));
        link.setToElementId(rs.getLong("to_element_id"));
        link.setLinkTypeId(rs.getLong("link_type_id"));
        return link;
    }
}

/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.openrqm.model.RQMWorkspace;
import org.springframework.jdbc.core.RowMapper;

public class WorkspaceRowMapper implements RowMapper<RQMWorkspace> {
    
    @Override
    public RQMWorkspace mapRow(ResultSet rs, int rowNum) throws SQLException {
        RQMWorkspace workspace = new RQMWorkspace();
        workspace.setId(rs.getLong("id"));
        workspace.setName(rs.getString("name"));
        workspace.setWorkspaceId(rs.getLong("workspace_id"));
        return workspace;
    }
}
/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.openrqm.model.RQMWorkspaceUser;
import org.springframework.jdbc.core.RowMapper;

public class WorkspaceUserRowMapper implements RowMapper<RQMWorkspaceUser> {

    @Override
    public RQMWorkspaceUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        RQMWorkspaceUser workspaceUser = new RQMWorkspaceUser();
        workspaceUser.setUserId(rs.getLong("user_id"));
        workspaceUser.setPermissions(rs.getLong("permissions"));
        return workspaceUser;
    }

}
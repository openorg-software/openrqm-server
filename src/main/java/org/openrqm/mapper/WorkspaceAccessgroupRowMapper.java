/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.openrqm.model.RQMWorkspaceAccessgroup;
import org.springframework.jdbc.core.RowMapper;

public class WorkspaceAccessgroupRowMapper implements RowMapper<RQMWorkspaceAccessgroup> {

    @Override
    public RQMWorkspaceAccessgroup mapRow(ResultSet rs, int rowNum) throws SQLException {
        RQMWorkspaceAccessgroup workspaceAccessgroup = new RQMWorkspaceAccessgroup();
        workspaceAccessgroup.setAccessGroupId(rs.getLong("accessgroup_id"));
        workspaceAccessgroup.setPermissions(rs.getLong("permissions"));
        return workspaceAccessgroup;
    }

}
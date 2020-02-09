/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.export;

import org.openrqm.model.RQMDocument;
import org.openrqm.model.RQMElements;
import org.springframework.core.io.Resource;

/**
 *
 * @author Marcel Jaehn <marcel.jaehn@online.de>
 */
public interface Exporter {
    abstract public Resource export(RQMDocument document, RQMElements elements, String templateName, String exportName) throws Exception;
}

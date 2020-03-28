/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.export;

import java.util.List;
import org.openrqm.model.RQMDocument;
import org.openrqm.model.RQMElement;
import org.springframework.core.io.Resource;

public interface Exporter {
    abstract public Resource export(RQMDocument document, List<RQMElement> elements, String templateName, String exportName) throws Exception;
}

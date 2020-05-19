/*
 * openrqm-server
 * SPDX-License-Identifier: GPL-2.0-only
 * Copyright (C) 2019 Marcel Jaehn
 */

package org.openrqm.importing;

public interface Importer {
    abstract public void importRQMFile(String importFilePath) throws Exception;
}

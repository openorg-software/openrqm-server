/*
openrqm-server
Entry point of the OpenRQM server implementation
SPDX-License-Identifier: GPL-2.0-only
Copyright (C) 2019 Marcel Jaehn
*/

package com.openrqm.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The OpenRQM server for requirements managing.
 */
public final class OpenRQMServer {
    final static Logger logger = LogManager.getLogger(OpenRQMServer.class);

    /**
     * Starts the OpenRQM server.
     * @param args The commandline arguments
     */
    public static void main(String[] args) {
        logger.info("Starting the OpenRQM server");
    }
}

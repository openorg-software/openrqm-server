 /*
openrqm-server
Entry point of the OpenRQM server implementation
SPDX-License-Identifier: GPL-2.0-only
Copyright (C) 2019 Marcel Jaehn
*/

package com.openrqm.server;

/**
 * The OpenRQM server for requirements managing.
 */
public final class OpenRQMServer {
    private OpenRQMServer() {
    }

    /**
     * Starts the OpenRQM server.
     * @param args The commandline arguments
     */
    public static void main(String[] args) {
        System.out.println("Starting the OpenRQM server");
    }
}

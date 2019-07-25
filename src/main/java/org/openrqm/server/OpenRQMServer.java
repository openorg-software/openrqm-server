/*
openrqm-server
Entry point of the OpenRQM server implementation
SPDX-License-Identifier: GPL-2.0-only
Copyright (C) 2019 Marcel Jaehn
*/

package org.openrqm.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The OpenRQM server for requirements managing.
 */
public final class OpenRQMServer {
    final static Logger logger = LogManager.getLogger(OpenRQMServer.class);

    /**
     * Starts the OpenRQM server.
     * 
     * @param args The commandline arguments
     */
    public static void main(String[] args) {
        logger.info("Starting the OpenRQM server at localhost:8090");
        Server server = NettyServerBuilder.forAddress(new InetSocketAddress("localhost", 8090))
                .addService(new OpenRQMServiceImpl()).build();
        try {
            server.start();
            server.awaitTermination();
        } catch (IOException e) {
            logger.error("Something bad happened");
        } catch (InterruptedException e) {
            logger.error("Interrupted server");
        }
        logger.info("Stopping the OpenRQM server");
    }
}

/*
openrqm-server
Entry point of the OpenRQM server implementation
SPDX-License-Identifier: GPL-2.0-only
Copyright (C) 2019 Marcel Jaehn
*/

package com.openrqm.server;

import java.io.IOException;

import com.openrqm.grpc.OpenRQMServiceGrpc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.grpc.Server;
import io.grpc.ServerBuilder;

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
        logger.info("Starting the OpenRQM server");
        Server server = ServerBuilder.forPort(8090).addService(new OpenRQMService()).build();
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

    private static class OpenRQMService extends OpenRQMServiceGrpc.OpenRQMServiceImplBase {
        /**
        */
        public void callOne(com.openrqm.grpc.OpenRQMGrpc.Object request,
                io.grpc.stub.StreamObserver<com.openrqm.grpc.OpenRQMGrpc.Empty> responseObserver) {
            logger.info("callTwo");
        }

        /**
         */
        public void callTwo(com.openrqm.grpc.OpenRQMGrpc.Empty request,
                io.grpc.stub.StreamObserver<com.openrqm.grpc.OpenRQMGrpc.Object> responseObserver) {
            logger.info("callTwo");
        }
    }
}

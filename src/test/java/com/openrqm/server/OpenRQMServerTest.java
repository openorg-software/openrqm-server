/*
openrqm-server
Tests for the entry point of the OpenRQM server implementation
SPDX-License-Identifier: GPL-2.0-only
Copyright (C) 2019 Marcel Jaehn
*/

package com.openrqm.server;

import org.junit.*;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.StatusRuntimeException;
import org.junit.Test;
import org.openrqm.grpc.OpenRQMServiceGrpc;
import org.openrqm.grpc.OpenRQMGrpc.Message;
import org.openrqm.server.OpenRQMServiceImpl;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for the OpenRQM server.
 */
public class OpenRQMServerTest {

    private static ManagedChannel channel;
    private static OpenRQMServiceGrpc.OpenRQMServiceBlockingStub blockingStub;
    private static Server server;

    /**
     * Sets up the test fixture. (Called before every test case method.)
     * 
     * @throws IOException
     */
    @BeforeClass
    public static void setUp() throws IOException {
        // setup server
        server = ServerBuilder.forPort(8090).addService(new OpenRQMServiceImpl()).build();
        server.start();

        // setup client
        channel = ManagedChannelBuilder.forAddress("localhost", 8090).usePlaintext().build();
        blockingStub = OpenRQMServiceGrpc.newBlockingStub(channel);
    }

    /**
     * Tears down the test fixture. (Called after every test case method.)
     * 
     * @throws InterruptedException
     */
    @AfterClass
    public static void tearDown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        server.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * OpenRQM server testing.
     */
    @Test
    public void testOpenRQMServer() {
        Message requestMessage = Message.newBuilder().setContent("test").build();
        Message responseMessage;
        try {
            responseMessage = blockingStub.echo(requestMessage);
        } catch (StatusRuntimeException e) {
            fail(e.getMessage());
            return;
        }
        assertEquals(requestMessage.getContent() + "!", responseMessage.getContent());
    }
}

package org.openrqm.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openrqm.grpc.OpenRQMServiceGrpc;
import org.openrqm.grpc.OpenRQMGrpc.Message;

import io.grpc.stub.StreamObserver;

public class OpenRQMServiceImpl extends OpenRQMServiceGrpc.OpenRQMServiceImplBase {
    final static Logger logger = LogManager.getLogger(OpenRQMServiceImpl.class);

    @Override
    public void echo(Message message, StreamObserver<Message> responseObserver) {
        logger.info("Received: " + message.getContent());
        responseObserver.onNext(Message.newBuilder().setContent(message.getContent() + "!").build());
        responseObserver.onCompleted();
    }
}
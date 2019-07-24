package com.openrqm.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.22.1)",
    comments = "Source: openrqm.proto")
public final class OpenRQMServiceGrpc {

  private OpenRQMServiceGrpc() {}

  public static final String SERVICE_NAME = "com.openrqm.grpc.OpenRQMService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.openrqm.grpc.OpenRQMGrpc.Object,
      com.openrqm.grpc.OpenRQMGrpc.Empty> getCallOneMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CallOne",
      requestType = com.openrqm.grpc.OpenRQMGrpc.Object.class,
      responseType = com.openrqm.grpc.OpenRQMGrpc.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.openrqm.grpc.OpenRQMGrpc.Object,
      com.openrqm.grpc.OpenRQMGrpc.Empty> getCallOneMethod() {
    io.grpc.MethodDescriptor<com.openrqm.grpc.OpenRQMGrpc.Object, com.openrqm.grpc.OpenRQMGrpc.Empty> getCallOneMethod;
    if ((getCallOneMethod = OpenRQMServiceGrpc.getCallOneMethod) == null) {
      synchronized (OpenRQMServiceGrpc.class) {
        if ((getCallOneMethod = OpenRQMServiceGrpc.getCallOneMethod) == null) {
          OpenRQMServiceGrpc.getCallOneMethod = getCallOneMethod = 
              io.grpc.MethodDescriptor.<com.openrqm.grpc.OpenRQMGrpc.Object, com.openrqm.grpc.OpenRQMGrpc.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.openrqm.grpc.OpenRQMService", "CallOne"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.openrqm.grpc.OpenRQMGrpc.Object.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.openrqm.grpc.OpenRQMGrpc.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new OpenRQMServiceMethodDescriptorSupplier("CallOne"))
                  .build();
          }
        }
     }
     return getCallOneMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.openrqm.grpc.OpenRQMGrpc.Empty,
      com.openrqm.grpc.OpenRQMGrpc.Object> getCallTwoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CallTwo",
      requestType = com.openrqm.grpc.OpenRQMGrpc.Empty.class,
      responseType = com.openrqm.grpc.OpenRQMGrpc.Object.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.openrqm.grpc.OpenRQMGrpc.Empty,
      com.openrqm.grpc.OpenRQMGrpc.Object> getCallTwoMethod() {
    io.grpc.MethodDescriptor<com.openrqm.grpc.OpenRQMGrpc.Empty, com.openrqm.grpc.OpenRQMGrpc.Object> getCallTwoMethod;
    if ((getCallTwoMethod = OpenRQMServiceGrpc.getCallTwoMethod) == null) {
      synchronized (OpenRQMServiceGrpc.class) {
        if ((getCallTwoMethod = OpenRQMServiceGrpc.getCallTwoMethod) == null) {
          OpenRQMServiceGrpc.getCallTwoMethod = getCallTwoMethod = 
              io.grpc.MethodDescriptor.<com.openrqm.grpc.OpenRQMGrpc.Empty, com.openrqm.grpc.OpenRQMGrpc.Object>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.openrqm.grpc.OpenRQMService", "CallTwo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.openrqm.grpc.OpenRQMGrpc.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.openrqm.grpc.OpenRQMGrpc.Object.getDefaultInstance()))
                  .setSchemaDescriptor(new OpenRQMServiceMethodDescriptorSupplier("CallTwo"))
                  .build();
          }
        }
     }
     return getCallTwoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OpenRQMServiceStub newStub(io.grpc.Channel channel) {
    return new OpenRQMServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OpenRQMServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new OpenRQMServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static OpenRQMServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new OpenRQMServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class OpenRQMServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void callOne(com.openrqm.grpc.OpenRQMGrpc.Object request,
        io.grpc.stub.StreamObserver<com.openrqm.grpc.OpenRQMGrpc.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getCallOneMethod(), responseObserver);
    }

    /**
     */
    public void callTwo(com.openrqm.grpc.OpenRQMGrpc.Empty request,
        io.grpc.stub.StreamObserver<com.openrqm.grpc.OpenRQMGrpc.Object> responseObserver) {
      asyncUnimplementedUnaryCall(getCallTwoMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCallOneMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.openrqm.grpc.OpenRQMGrpc.Object,
                com.openrqm.grpc.OpenRQMGrpc.Empty>(
                  this, METHODID_CALL_ONE)))
          .addMethod(
            getCallTwoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.openrqm.grpc.OpenRQMGrpc.Empty,
                com.openrqm.grpc.OpenRQMGrpc.Object>(
                  this, METHODID_CALL_TWO)))
          .build();
    }
  }

  /**
   */
  public static final class OpenRQMServiceStub extends io.grpc.stub.AbstractStub<OpenRQMServiceStub> {
    private OpenRQMServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OpenRQMServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OpenRQMServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OpenRQMServiceStub(channel, callOptions);
    }

    /**
     */
    public void callOne(com.openrqm.grpc.OpenRQMGrpc.Object request,
        io.grpc.stub.StreamObserver<com.openrqm.grpc.OpenRQMGrpc.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCallOneMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void callTwo(com.openrqm.grpc.OpenRQMGrpc.Empty request,
        io.grpc.stub.StreamObserver<com.openrqm.grpc.OpenRQMGrpc.Object> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCallTwoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class OpenRQMServiceBlockingStub extends io.grpc.stub.AbstractStub<OpenRQMServiceBlockingStub> {
    private OpenRQMServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OpenRQMServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OpenRQMServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OpenRQMServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.openrqm.grpc.OpenRQMGrpc.Empty callOne(com.openrqm.grpc.OpenRQMGrpc.Object request) {
      return blockingUnaryCall(
          getChannel(), getCallOneMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.openrqm.grpc.OpenRQMGrpc.Object callTwo(com.openrqm.grpc.OpenRQMGrpc.Empty request) {
      return blockingUnaryCall(
          getChannel(), getCallTwoMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class OpenRQMServiceFutureStub extends io.grpc.stub.AbstractStub<OpenRQMServiceFutureStub> {
    private OpenRQMServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OpenRQMServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OpenRQMServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OpenRQMServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.openrqm.grpc.OpenRQMGrpc.Empty> callOne(
        com.openrqm.grpc.OpenRQMGrpc.Object request) {
      return futureUnaryCall(
          getChannel().newCall(getCallOneMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.openrqm.grpc.OpenRQMGrpc.Object> callTwo(
        com.openrqm.grpc.OpenRQMGrpc.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getCallTwoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CALL_ONE = 0;
  private static final int METHODID_CALL_TWO = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final OpenRQMServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(OpenRQMServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALL_ONE:
          serviceImpl.callOne((com.openrqm.grpc.OpenRQMGrpc.Object) request,
              (io.grpc.stub.StreamObserver<com.openrqm.grpc.OpenRQMGrpc.Empty>) responseObserver);
          break;
        case METHODID_CALL_TWO:
          serviceImpl.callTwo((com.openrqm.grpc.OpenRQMGrpc.Empty) request,
              (io.grpc.stub.StreamObserver<com.openrqm.grpc.OpenRQMGrpc.Object>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class OpenRQMServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    OpenRQMServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.openrqm.grpc.OpenRQMGrpc.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("OpenRQMService");
    }
  }

  private static final class OpenRQMServiceFileDescriptorSupplier
      extends OpenRQMServiceBaseDescriptorSupplier {
    OpenRQMServiceFileDescriptorSupplier() {}
  }

  private static final class OpenRQMServiceMethodDescriptorSupplier
      extends OpenRQMServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    OpenRQMServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (OpenRQMServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new OpenRQMServiceFileDescriptorSupplier())
              .addMethod(getCallOneMethod())
              .addMethod(getCallTwoMethod())
              .build();
        }
      }
    }
    return result;
  }
}

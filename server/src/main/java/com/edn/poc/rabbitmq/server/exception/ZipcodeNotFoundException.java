package com.edn.poc.rabbitmq.server.exception;

public class ZipcodeNotFoundException extends Exception {
    public ZipcodeNotFoundException() {
        super();
    }

    public ZipcodeNotFoundException(String message) {
        super(message);
    }

    public ZipcodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZipcodeNotFoundException(Throwable cause) {
        super(cause);
    }
}

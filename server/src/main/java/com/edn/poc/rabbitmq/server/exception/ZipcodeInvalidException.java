package com.edn.poc.rabbitmq.server.exception;

public class ZipcodeInvalidException extends Exception {

    public ZipcodeInvalidException() {
        super();
    }

    public ZipcodeInvalidException(String message) {
        super(message);
    }

    public ZipcodeInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZipcodeInvalidException(Throwable cause) {
        super(cause);
    }
}

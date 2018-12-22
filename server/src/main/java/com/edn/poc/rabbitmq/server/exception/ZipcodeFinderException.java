package com.edn.poc.rabbitmq.server.exception;

public class ZipcodeFinderException extends Exception {

    public ZipcodeFinderException() {
        super();
    }

    public ZipcodeFinderException(String message) {
        super(message);
    }

    public ZipcodeFinderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZipcodeFinderException(Throwable cause) {
        super(cause);
    }
}

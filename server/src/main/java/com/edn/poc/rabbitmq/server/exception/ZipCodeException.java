package com.edn.poc.rabbitmq.server.exception;

public class ZipCodeException extends Exception {

    public ZipCodeException() {
        super();
    }

    public ZipCodeException(String message) {
        super(message);
    }

    public ZipCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZipCodeException(Throwable cause) {
        super(cause);
    }
}

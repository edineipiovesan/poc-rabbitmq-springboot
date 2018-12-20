package com.edn.poc.rabbitmq.client.exception;

public class ZipCodeGeneratorException extends Exception {
    public ZipCodeGeneratorException() {
        super();
    }

    public ZipCodeGeneratorException(String message) {
        super(message);
    }

    public ZipCodeGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZipCodeGeneratorException(Throwable cause) {
        super(cause);
    }
}

package com.edn.poc.rabbitmq.server.finder.impl.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;

public class UtilComponents {

    private UtilComponents() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static ObjectMapper getObjectMapper() {
        return new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}

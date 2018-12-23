package com.edn.poc.rabbitmq.server.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class ComponentUtils {

    private ComponentUtils() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static ObjectMapper getObjectMapper() {
        return new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}

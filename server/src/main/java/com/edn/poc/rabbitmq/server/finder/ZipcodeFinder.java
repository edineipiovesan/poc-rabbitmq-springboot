package com.edn.poc.rabbitmq.server.finder;

import com.edn.poc.rabbitmq.server.exception.ApiRequestException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeInvalidException;
import com.edn.poc.rabbitmq.server.model.IAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.GenericTypeResolver;

import java.io.IOException;

import static com.edn.poc.rabbitmq.server.util.StandardUtils.standardizeZipcode;

public abstract class ZipcodeFinder<T extends IAddress> {

    public abstract String getApiName();

    protected abstract ObjectMapper getObjectMapper();

    protected abstract String request(String zipcode) throws ApiRequestException;

    public IAddress find(String zipcode) throws ZipcodeInvalidException, ApiRequestException {
        String standardizedZipcode = standardizeZipcode(zipcode);

        try {
            String json = request(standardizedZipcode);

            Class<T> genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), ZipcodeFinder.class);
            return getObjectMapper().readValue(json, genericType);
        } catch (IOException e) {
            throw new ApiRequestException("Cannot deserialize JSON response from " + getApiName());
        }
    }
}

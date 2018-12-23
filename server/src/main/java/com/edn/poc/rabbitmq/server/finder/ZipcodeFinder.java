package com.edn.poc.rabbitmq.server.finder;

import com.edn.poc.rabbitmq.server.exception.ApiRequestException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeNotFoundException;
import com.edn.poc.rabbitmq.server.model.IAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import org.springframework.core.GenericTypeResolver;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public abstract class ZipcodeFinder<T extends IAddress> {

    public abstract String getApiName();

    protected abstract ObjectMapper getObjectMapper();

    protected abstract HttpResponse<String> request(String zipcode) throws ApiRequestException;

    public IAddress find(String zipcode) throws ZipcodeNotFoundException {
        try {
            HttpResponse<String> response = request(zipcode);
            if (response.getStatus() != HttpStatus.OK.value())
                throw new ZipcodeNotFoundException("Request cannot be processed by " + getApiName());

            String json = response.getBody();

            Class<T> genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), ZipcodeFinder.class);
            return getObjectMapper().readValue(json, genericType);
        } catch (IOException e) {
            throw new ZipcodeNotFoundException("Cannot deserialize JSON response from " + getApiName());
        } catch (ApiRequestException e) {
            throw new ZipcodeNotFoundException(e.getMessage());
        }
    }
}

package com.edn.poc.rabbitmq.server.finder;

import com.edn.poc.rabbitmq.server.exception.ApiRequestException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeInvalidException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeNotFoundException;
import com.edn.poc.rabbitmq.server.model.IAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import org.springframework.core.GenericTypeResolver;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.IOException;

public abstract class ZipcodeFinder<T extends IAddress> {

    protected abstract String getApiName();

    protected abstract ObjectMapper getObjectMapper();

    protected abstract HttpResponse<String> request(String zipcode) throws ApiRequestException;

    public IAddress find(String zipcode) throws ZipcodeNotFoundException, ZipcodeInvalidException {
        zipcode = standardizeZipcode(zipcode);

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

    private String standardizeZipcode(String zipcode) throws ZipcodeInvalidException {
        if (StringUtils.isEmpty(zipcode))
            throw new ZipcodeInvalidException(String.format("Zipcode %s is not valid. Please provida a zipcode in " +
                    "standard 12345-678 or 12345678.", zipcode));

        String hyphenPattern = "^\\d{5}[-]\\d{3}$";
        String onlyNumberPattern = "^\\d{8}$";

        if (zipcode.matches(onlyNumberPattern))
            return zipcode;

        if (zipcode.matches(hyphenPattern))
            return StringUtils.delete(zipcode, "-");

        throw new ZipcodeInvalidException(String.format("Zipcode %s is not valid. Please provida a zipcode in " +
                "standard 12345-678 or 12345678.", zipcode));
    }
}

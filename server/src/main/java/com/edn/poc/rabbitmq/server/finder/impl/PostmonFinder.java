package com.edn.poc.rabbitmq.server.finder.impl;

import com.edn.poc.rabbitmq.server.configuration.api.ApiInfo;
import com.edn.poc.rabbitmq.server.exception.ApiRequestException;
import com.edn.poc.rabbitmq.server.finder.ZipcodeFinder;
import com.edn.poc.rabbitmq.server.model.postmon.PostmonAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.log4j.Log4j2;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("PostmonFinder")
@Log4j2
public class PostmonFinder extends ZipcodeFinder<PostmonAddress> {

    private final ApiInfo apiInfo;

    private final ObjectMapper objectMapper;

    @Autowired
    public PostmonFinder(@Qualifier("PostmonApiInfo") ApiInfo apiInfo, ObjectMapper objectMapper) {
        this.apiInfo = apiInfo;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getApiName() {
        return "Postmon";
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Override
    public String request(String zipcode) throws ApiRequestException {
        String url = apiInfo.getUrl();
        String endpoint = apiInfo.getEndpoint();

        String fullUrl = String.format("%s/%s/%s", url, endpoint, zipcode);

        try {
            log.info("Sending GET to {}", fullUrl);
            HttpResponse<String> response = Unirest.get(fullUrl)
                    .header("Content-Type", ContentType.APPLICATION_JSON.getMimeType())
                    .header("Accept", ContentType.APPLICATION_JSON.getMimeType())
                    .asString();

            if (response.getStatus() != HttpStatus.OK.value())
                throw new ApiRequestException("API request failed.");

            return response.getBody();
        } catch (UnirestException e) {
            throw new ApiRequestException("API communication error", e);
        }
    }
}

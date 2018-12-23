package com.edn.poc.rabbitmq.server.finder.impl;

import com.edn.poc.rabbitmq.server.configuration.api.ApiInfo;
import com.edn.poc.rabbitmq.server.exception.ApiRequestException;
import com.edn.poc.rabbitmq.server.finder.ZipcodeFinder;
import com.edn.poc.rabbitmq.server.model.cepaberto.CEPAbertoAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.log4j.Log4j2;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("CEPAbertoFinder")
@Log4j2
public class CEPAbertoFinder extends ZipcodeFinder<CEPAbertoAddress> {

    private final ApiInfo apiInfo;
    private final ObjectMapper objectMapper;

    @Autowired
    public CEPAbertoFinder(@Qualifier("CEPAbertoApiInfo") ApiInfo apiInfo, ObjectMapper objectMapper) {
        this.apiInfo = apiInfo;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getApiName() {
        return "CEP Aberto";
    }

    @Override
    public com.fasterxml.jackson.databind.ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public HttpResponse<String> request(String zipcode) throws ApiRequestException {
        String url = apiInfo.getUrl();
        String endpoint = apiInfo.getEndpoint();
        String token = apiInfo.getToken();

        String urlEndpoint = url + endpoint;
        String tokenHeader = "Token " + token;

        try {
            log.info("Sending GET to {}", urlEndpoint);
            return Unirest.get(urlEndpoint)
                    .queryString("cep", zipcode)
                    .header("Authorization", tokenHeader)
                    .header("Content-Type", ContentType.APPLICATION_JSON.getMimeType())
                    .header("Accept", ContentType.APPLICATION_JSON.getMimeType())
                    .asString();
        } catch (UnirestException e) {
            throw new ApiRequestException("API communication error", e);
        }
    }
}

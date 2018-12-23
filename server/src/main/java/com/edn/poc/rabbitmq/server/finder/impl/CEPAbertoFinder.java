package com.edn.poc.rabbitmq.server.finder.impl;

import com.edn.poc.rabbitmq.server.configuration.api.impl.CEPAbertoApiInfo;
import com.edn.poc.rabbitmq.server.exception.ApiRequestException;
import com.edn.poc.rabbitmq.server.finder.ZipcodeFinder;
import com.edn.poc.rabbitmq.server.model.cepaberto.CEPAbertoAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CEPAbertoFinder extends ZipcodeFinder<CEPAbertoAddress> {

    private final CEPAbertoApiInfo apiInfo;
    private final ObjectMapper objectMapper;

    @Autowired
    public CEPAbertoFinder(CEPAbertoApiInfo apiInfo, ObjectMapper objectMapper) {
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
            return Unirest.get(urlEndpoint)
                    .queryString("cep", zipcode)
                    .header("Authorization", tokenHeader)
                    .header("Content-Type", ContentType.APPLICATION_JSON.getMimeType())
                    .header("Accept", ContentType.APPLICATION_JSON.getMimeType())
                    .asString();
        } catch (UnirestException e) {
            throw new ApiRequestException("Can not request zpicode informations from " + getApiName(), e);
        }
    }
}

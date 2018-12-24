package com.edn.poc.rabbitmq.server.provider.service.impl;

import com.edn.poc.rabbitmq.server.provider.api.ApiInfo;
import com.edn.poc.rabbitmq.server.exception.ApiRequestException;
import com.edn.poc.rabbitmq.server.provider.service.ZipcodeProvider;
import com.edn.poc.rabbitmq.server.provider.model.cepaberto.CEPAbertoAddress;
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

@Service("CEPAbertoFinder")
@Log4j2
public class CEPAbertoProvider extends ZipcodeProvider<CEPAbertoAddress> {

    private final ApiInfo apiInfo;
    private final ObjectMapper objectMapper;

    @Autowired
    public CEPAbertoProvider(@Qualifier("CEPAbertoApiInfo") ApiInfo apiInfo, ObjectMapper objectMapper) {
        this.apiInfo = apiInfo;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getApiName() {
        return "CEP Aberto";
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public String request(String zipcode) throws ApiRequestException {
        String url = apiInfo.getUrl();
        String endpoint = apiInfo.getEndpoint();
        String token = apiInfo.getToken();

        String urlEndpoint = url + endpoint;
        String tokenHeader = "Token " + token;

        try {
            log.info("Sending GET to {}", urlEndpoint);
            HttpResponse<String> response = Unirest.get(urlEndpoint)
                    .queryString("cep", zipcode)
                    .header("Authorization", tokenHeader)
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

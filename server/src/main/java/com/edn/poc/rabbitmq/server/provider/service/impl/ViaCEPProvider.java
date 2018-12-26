package com.edn.poc.rabbitmq.server.provider.service.impl;

import com.edn.poc.rabbitmq.server.exception.ApiRequestException;
import com.edn.poc.rabbitmq.server.provider.api.ApiInfo;
import com.edn.poc.rabbitmq.server.provider.model.viacep.ViaCEPAddress;
import com.edn.poc.rabbitmq.server.provider.service.ZipcodeProvider;
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

@Service("ViaCEPFinder")
@Log4j2
public class ViaCEPProvider extends ZipcodeProvider<ViaCEPAddress> {

    private final ApiInfo apiInfo;

    private final ObjectMapper objectMapper;

    @Autowired
    public ViaCEPProvider(@Qualifier("ViaCEPApiInfo") ApiInfo apiInfo, ObjectMapper objectMapper) {
        this.apiInfo = apiInfo;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getApiName() {
        return "Via CEP";
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Override
    public String request(String zipcode) throws ApiRequestException {
        String url = apiInfo.getUrl();
        String endpoint = apiInfo.getEndpoint();
        String format = apiInfo.getFormat();

        String fullUrl = String.format("%s/%s/%s/%s", url, endpoint, zipcode, format);

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

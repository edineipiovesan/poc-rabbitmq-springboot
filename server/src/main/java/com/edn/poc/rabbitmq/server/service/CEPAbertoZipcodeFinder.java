package com.edn.poc.rabbitmq.server.service;

import com.edn.poc.rabbitmq.server.configuration.ZipCodeAPIConfig;
import com.edn.poc.rabbitmq.server.exception.ZipCodeException;
import com.edn.poc.rabbitmq.server.finder.ZipcodeFinder;
import com.edn.poc.rabbitmq.server.model.impl.CEPAbertoAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CEPAbertoZipcodeFinder implements ZipcodeFinder {

    @Autowired
    private ZipCodeAPIConfig zipCodeAPIConfig;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    public CEPAbertoAddress find(String zipcode) throws ZipCodeException {
        String url = zipCodeAPIConfig.getUrl();
        String endpoint = zipCodeAPIConfig.getEndpoint();
        String format = zipCodeAPIConfig.getFormat();
        String token = zipCodeAPIConfig.getToken();

        System.out.println(zipcode);
        System.out.println(url);
        System.out.println(format);
        System.out.println(token);

        String urlEndpoint = String.format("%s%s", url, endpoint);
        System.out.println(urlEndpoint);

        String tokenHeader = String.format("Token %s", token);
        System.out.println(tokenHeader);

        try {
            HttpResponse<String> json = Unirest.get(urlEndpoint)
                    .queryString("cep", zipcode)
                    .header("Authorization", tokenHeader)
                    .header("Content-Type", ContentType.APPLICATION_JSON.getMimeType())
                    .header("Accept", ContentType.APPLICATION_JSON.getMimeType())
                    .asString();

            System.out.println(json.getStatus());
            System.out.println(json.getStatusText());
            System.out.println(json.getHeaders());
            System.out.println(json.getBody());

            return jacksonObjectMapper.readValue(json.getBody(), CEPAbertoAddress.class);
        } catch (UnirestException | IOException e) {
            e.printStackTrace();
            throw new ZipCodeException("API communication error.");
        }
    }
}

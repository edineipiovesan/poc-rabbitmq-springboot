package com.edn.poc.rabbitmq.server.finder.impl;

import com.edn.poc.rabbitmq.server.configuration.api.impl.CEPAbertoApiInfo;
import com.edn.poc.rabbitmq.server.dto.response.AddressResponse;
import com.edn.poc.rabbitmq.server.dto.response.IAddress;
import com.edn.poc.rabbitmq.server.exception.ZipcodeFinderException;
import com.edn.poc.rabbitmq.server.finder.ZipcodeFinder;
import com.edn.poc.rabbitmq.server.model.impl.CEPAbertoAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CEPAbertoZipcodeFinder implements ZipcodeFinder {

    @Autowired
    private CEPAbertoApiInfo cepAbertoApiInfo;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    public IAddress find(String zipcode) throws ZipcodeFinderException {
        System.out.println("Iniciado a chamada na API " + getClass().getSimpleName());

        String url = cepAbertoApiInfo.getUrl();
        String endpoint = cepAbertoApiInfo.getEndpoint();
        String token = cepAbertoApiInfo.getToken();

        String urlEndpoint = url + endpoint;
        String tokenHeader = "Token " + token;

        try {
            System.out.println("Request sent to: " + urlEndpoint);
            System.out.println("Token header: " + tokenHeader);

            HttpResponse<String> json = Unirest.get(urlEndpoint)
                    .queryString("cep", zipcode)
                    .header("Authorization", tokenHeader)
                    .header("Content-Type", ContentType.APPLICATION_JSON.getMimeType())
                    .header("Accept", ContentType.APPLICATION_JSON.getMimeType())
                    .asString();

            System.out.println("Response: " + json.getBody());
            if (json.getStatus() != HttpStatus.OK.value())
                throw new UnirestException("Request cannot be processed by " + getClass().getSimpleName());

            CEPAbertoAddress cepAbertoAddress = jacksonObjectMapper.readValue(json.getBody(), CEPAbertoAddress.class);
            return new AddressResponse(cepAbertoAddress);
        } catch (UnirestException | IOException e) {
            throw new ZipcodeFinderException("API communication error.");
        } catch (NullPointerException e) {
            throw new ZipcodeFinderException(getClass().getSimpleName() + " cannot retrieve all informations needed.");
        }
    }
}

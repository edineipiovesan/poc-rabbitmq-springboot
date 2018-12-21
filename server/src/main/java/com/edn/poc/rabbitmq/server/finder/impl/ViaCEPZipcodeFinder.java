package com.edn.poc.rabbitmq.server.finder.impl;

import com.edn.poc.rabbitmq.server.configuration.api.impl.ViaCEPApiInfo;
import com.edn.poc.rabbitmq.server.dto.response.AddressResponse;
import com.edn.poc.rabbitmq.server.dto.response.IAddress;
import com.edn.poc.rabbitmq.server.exception.ZipcodeFinderException;
import com.edn.poc.rabbitmq.server.finder.ZipcodeFinder;
import com.edn.poc.rabbitmq.server.model.impl.ViaCEPAddress;
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
public class ViaCEPZipcodeFinder implements ZipcodeFinder {

    @Autowired
    private ViaCEPApiInfo viaCEPApiInfo;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Override
    public IAddress find(String zipcode) throws ZipcodeFinderException {
        System.out.println("Iniciado a chamada na API " + getClass().getSimpleName());

        String url = viaCEPApiInfo.getUrl();
        String endpoint = viaCEPApiInfo.getEndpoint();
        String format = viaCEPApiInfo.getFormat();

        String fullUrl = String.format("%s/%s/%s/%s", url, endpoint, zipcode, format);

        try {
            System.out.println("Request sent to: " + fullUrl);
            HttpResponse<String> json = Unirest.get(fullUrl)
                    .header("Content-Type", ContentType.APPLICATION_JSON.getMimeType())
                    .header("Accept", ContentType.APPLICATION_JSON.getMimeType())
                    .asString();

            System.out.println("Response: " + json.getBody());
            if (json.getStatus() != HttpStatus.OK.value())
                throw new UnirestException("Request cannot be processed by " + getClass().getSimpleName());

            ViaCEPAddress viaCEPAddress = jacksonObjectMapper.readValue(json.getBody(), ViaCEPAddress.class);
            return new AddressResponse(viaCEPAddress);
        } catch (UnirestException | IOException e) {
            throw new ZipcodeFinderException("API communication error.");
        } catch (NullPointerException e) {
            throw new ZipcodeFinderException(getClass().getSimpleName() + " cannot retrieve all informations needed.");
        }
    }
}

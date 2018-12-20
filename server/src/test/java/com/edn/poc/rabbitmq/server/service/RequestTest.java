package com.edn.poc.rabbitmq.server.service;

import com.edn.poc.rabbitmq.server.model.impl.CEPAbertoAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.entity.ContentType;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

public class RequestTest {

    @Test
    public void test() throws UnirestException {
        HttpResponse<String> json = Unirest.get("http://www.cepaberto.com/api/v3/cep")
                .queryString("cep", "74393250")
                .header("Authorization", "Token token=" + "6fa935c35e88f2e15a9e5330493e5645")
                .header("Content-Type", ContentType.APPLICATION_JSON.getMimeType())
                .header("Accept", ContentType.APPLICATION_JSON.getMimeType())
                .asString();

        System.out.println(json.getBody());
        assertThat(json.getBody()).isNotNull();
    }

    @Test
    public void mapper() throws IOException {
        String json = "{\"altitude\":779.4,\"cep\":\"74393250\",\"latitude\":\"-16.6868912\",\"longitude\":\"-49.2647943\"" +
                ",\"logradouro\":\"Rua FP 27\",\"bairro\":\"Recreio do Funcionário Público\",\"cidade\":{\"ddd\":62,\"" +
                "ibge\":\"5208707\",\"nome\":\"Goiânia\"},\"estado\":{\"sigla\":\"GO\"}}";

        ObjectMapper jacksonObjectMapper = new ObjectMapper();
        CEPAbertoAddress CEPAbertoAddress = jacksonObjectMapper.readValue(json, CEPAbertoAddress.class);
        System.out.println(CEPAbertoAddress);
        assertThat(CEPAbertoAddress.getCidade().getNome()).isEqualTo("Goiânia");
    }
}

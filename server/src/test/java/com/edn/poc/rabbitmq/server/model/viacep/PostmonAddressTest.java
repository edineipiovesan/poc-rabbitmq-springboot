package com.edn.poc.rabbitmq.server.model.viacep;

import com.edn.poc.rabbitmq.server.model.postmon.PostmonAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

public class PostmonAddressTest {

    private String json;

    @Before
    public void init() {
        json = "{\n" +
                "    \"bairro\": \"Recreio do Funcionário Público\",\n" +
                "    \"cidade\": \"Goiânia\",\n" +
                "    \"logradouro\": \"Rua FP27\",\n" +
                "    \"estado_info\": {\n" +
                "        \"area_km2\": \"340.110,385\",\n" +
                "        \"codigo_ibge\": \"52\",\n" +
                "        \"nome\": \"Goiás\"\n" +
                "    },\n" +
                "    \"cep\": \"74393250\",\n" +
                "    \"cidade_info\": {\n" +
                "        \"area_km2\": \"728,841\",\n" +
                "        \"codigo_ibge\": \"5208707\"\n" +
                "    },\n" +
                "    \"estado\": \"GO\"\n" +
                "}";
    }

    @Test
    public void deserializeTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        PostmonAddress address = objectMapper.readValue(json, PostmonAddress.class);

        assertThat(address.getCep()).isEqualTo("74393250");
        assertThat(address.getLogradouro()).isEqualTo("Rua FP27");
        assertThat(address.getBairro()).isEqualTo("Recreio do Funcionário Público");

        assertThat(address.getCidade()).isEqualTo("Goiânia");
        assertThat(address.getCidadePostmon().getAreaKm2()).isEqualTo("728,841");
        assertThat(address.getCidadePostmon().getCodigoIbge()).isEqualTo("5208707");

        assertThat(address.getEstadoSigla()).isEqualTo("GO");
        assertThat(address.getPostmonEstado().getAreaKm2()).isEqualTo("340.110,385");
        assertThat(address.getPostmonEstado().getCodigoIbge()).isEqualTo("52");
        assertThat(address.getPostmonEstado().getNome()).isEqualTo("Goiás");
    }
}

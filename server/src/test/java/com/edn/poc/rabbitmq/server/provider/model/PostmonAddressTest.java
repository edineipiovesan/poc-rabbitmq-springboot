package com.edn.poc.rabbitmq.server.provider.model;

import com.edn.poc.rabbitmq.server.provider.model.postmon.PostmonAddress;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.edn.poc.rabbitmq.server.util.ComponentUtils.getObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

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
        IAddress address = getObjectMapper().readValue(json, PostmonAddress.class);

        assertThat(address.getCep()).isEqualTo("74393250");
        assertThat(address.getLogradouro()).isEqualTo("Rua FP27");
        assertThat(address.getBairro()).isEqualTo("Recreio do Funcionário Público");

        assertThat(address.getCidade()).isEqualTo("Goiânia");
        assertThat(address.getEstado()).isEqualTo("Goiás");
    }
}

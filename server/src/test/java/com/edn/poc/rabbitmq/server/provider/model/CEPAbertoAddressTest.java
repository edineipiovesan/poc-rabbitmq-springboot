package com.edn.poc.rabbitmq.server.provider.model;

import com.edn.poc.rabbitmq.server.provider.model.cepaberto.CEPAbertoAddress;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.edn.poc.rabbitmq.server.util.ComponentUtils.getObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

public class CEPAbertoAddressTest {

    private String json;

    @Before
    public void init() {
        json = "{\n" +
                "    \"altitude\": 1137.1,\n" +
                "    \"cep\": \"72006296\",\n" +
                "    \"latitude\": \"-15.8111982\",\n" +
                "    \"longitude\": \"-48.0435267\",\n" +
                "    \"logradouro\": \"Rua 4 Chácara 288\",\n" +
                "    \"bairro\": \"Setor Habitacional Vicente Pires\",\n" +
                "    \"cidade\": {\n" +
                "        \"ddd\": 61,\n" +
                "        \"ibge\": \"5300108\",\n" +
                "        \"nome\": \"Brasília\"\n" +
                "    },\n" +
                "    \"estado\": {\n" +
                "        \"sigla\": \"DF\"\n" +
                "    }\n" +
                "}";
    }

    @Test
    public void deserializeTest() throws IOException {
        IAddress address = getObjectMapper().readValue(json, CEPAbertoAddress.class);

        assertThat(address.getCep()).isEqualTo("72006296");
        assertThat(address.getLogradouro()).isEqualTo("Rua 4 Chácara 288");
        assertThat(address.getBairro()).isEqualTo("Setor Habitacional Vicente Pires");

        assertThat(address.getCidade()).isEqualTo("Brasília");
        assertThat(address.getEstado()).isEqualTo("DF");
    }

}

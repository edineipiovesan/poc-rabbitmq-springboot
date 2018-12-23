package com.edn.poc.rabbitmq.server.model.viacep;

import com.edn.poc.rabbitmq.server.model.cepaberto.CEPAbertoAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

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
        ObjectMapper objectMapper = new ObjectMapper();

        CEPAbertoAddress address = objectMapper.readValue(json, CEPAbertoAddress.class);

        assertThat(address.getAltitude()).isEqualTo(1137.1);
        assertThat(address.getCep()).isEqualTo("72006296");
        assertThat(address.getLatitude()).isEqualTo("-15.8111982");
        assertThat(address.getLongitude()).isEqualTo("-48.0435267");
        assertThat(address.getLogradouro()).isEqualTo("Rua 4 Chácara 288");
        assertThat(address.getBairro()).isEqualTo("Setor Habitacional Vicente Pires");

        assertThat(address.getCEPAbertoCidade().getNome()).isEqualTo("Brasília");
        assertThat(address.getCEPAbertoCidade().getDdd()).isEqualTo(61);
        assertThat(address.getCEPAbertoCidade().getIbge()).isEqualTo("5300108");

        assertThat(address.getCEPAbertoEstado().getSigla()).isEqualTo("DF");
    }

}

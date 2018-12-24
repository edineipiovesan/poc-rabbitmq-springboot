package com.edn.poc.rabbitmq.server.provider.model;

import com.edn.poc.rabbitmq.server.provider.model.viacep.ViaCEPAddress;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.edn.poc.rabbitmq.server.util.ComponentUtils.getObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

public class ViaCEPAddressTest {

    private String json;
    private String error;

    @Before
    public void init() {
        json = "{\n" +
                "    \"cep\": \"87708-427\",\n" +
                "    \"logradouro\": \"Rua Janete Dias dos Santos\",\n" +
                "    \"complemento\": \"\",\n" +
                "    \"bairro\": \"Hélio Lopes\",\n" +
                "    \"localidade\": \"Paranavaí\",\n" +
                "    \"uf\": \"PR\",\n" +
                "    \"unidade\": \"\",\n" +
                "    \"ibge\": \"4118402\",\n" +
                "    \"gia\": \"\"\n" +
                "}";

        error = "{\n" +
                "    \"erro\": true\n" +
                "}";
    }

    @Test
    public void deserializeTest() throws IOException {
        IAddress address = getObjectMapper().readValue(json, ViaCEPAddress.class);

        assertThat(address.getCep()).isEqualTo("87708-427");
        assertThat(address.getLogradouro()).isEqualTo("Rua Janete Dias dos Santos");
        assertThat(address.getBairro()).isEqualTo("Hélio Lopes");

        assertThat(address.getCidade()).isEqualTo("Paranavaí");
        assertThat(address.getEstado()).isEqualTo("PR");
    }

    @Test(expected = MismatchedInputException.class)
    public void deserializeErrorTest() throws IOException {
        getObjectMapper().readValue(error, ViaCEPAddress.class);
    }
}

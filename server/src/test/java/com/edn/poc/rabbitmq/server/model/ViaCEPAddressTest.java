package com.edn.poc.rabbitmq.server.model;

import com.edn.poc.rabbitmq.server.model.viacep.ViaCEPAddress;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.edn.poc.rabbitmq.server.finder.impl.util.UtilComponents.*;
import static org.assertj.core.api.Assertions.*;

public class ViaCEPAddressTest {
    private String json;

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
}

package com.edn.poc.rabbitmq.server.provider.service;

import com.edn.poc.rabbitmq.server.exception.ApiRequestException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeInvalidException;
import com.edn.poc.rabbitmq.server.provider.api.ApiInfo;
import com.edn.poc.rabbitmq.server.provider.model.IAddress;
import com.edn.poc.rabbitmq.server.provider.service.impl.PostmonProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.edn.poc.rabbitmq.server.util.ComponentUtils.getObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostmonProviderTest {

    @Mock
    private ApiInfo apiInfo;

    private ZipcodeProvider finder;

    @Before
    public void init() {
        when(apiInfo.getUrl()).thenReturn("https://api.postmon.com.br/");
        when(apiInfo.getEndpoint()).thenReturn("v1/cep/");

        finder = new PostmonProvider(apiInfo, getObjectMapper());
    }

    @Test
    public void findTest() throws ZipcodeInvalidException, ApiRequestException {
        String zipcode = "74393250";
        IAddress address = finder.find(zipcode);

        assertThat(address).isNotNull();
        assertThat(address.getCep()).isEqualTo(zipcode);
        assertThat(address.getLogradouro()).isEqualTo("Rua FP27");
        assertThat(address.getBairro()).isEqualTo("Recreio do Funcionário Público");
        assertThat(address.getCidade()).isEqualTo("Goiânia");
        assertThat(address.getEstado()).isEqualTo("Goiás");
    }

    @Test(expected = ZipcodeInvalidException.class)
    public void zipcodeNullTest() throws ZipcodeInvalidException, ApiRequestException {
        finder.find(null);
    }

    @Test(expected = ZipcodeInvalidException.class)
    public void zipcodeEmptyStringTest() throws ZipcodeInvalidException, ApiRequestException {
        finder.find("");
    }

    @Test(expected = ZipcodeInvalidException.class)
    public void invalidZipcodeWithLetters() throws ZipcodeInvalidException, ApiRequestException {
        finder.find("AAA393250");
    }

    @Test(expected = ZipcodeInvalidException.class)
    public void invalidZipcodeWith5Digits() throws ZipcodeInvalidException, ApiRequestException {
        finder.find("74393");
    }

    @Test(expected = ZipcodeInvalidException.class)
    public void invalidZipcodeWith13Digits() throws ZipcodeInvalidException, ApiRequestException {
        finder.find("7439325001257");
    }
}

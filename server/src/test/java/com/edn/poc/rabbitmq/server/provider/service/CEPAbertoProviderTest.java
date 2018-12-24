package com.edn.poc.rabbitmq.server.provider.service;

import com.edn.poc.rabbitmq.server.provider.api.ApiInfo;
import com.edn.poc.rabbitmq.server.exception.ApiRequestException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeInvalidException;
import com.edn.poc.rabbitmq.server.provider.model.IAddress;
import com.edn.poc.rabbitmq.server.provider.service.impl.CEPAbertoProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.edn.poc.rabbitmq.server.util.ComponentUtils.getObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CEPAbertoProviderTest {

    @Mock
    private ApiInfo apiInfo;

    private ZipcodeProvider finder;

    @Before
    public void init() {
        when(apiInfo.getUrl()).thenReturn("http://www.cepaberto.com/");
        when(apiInfo.getEndpoint()).thenReturn("api/v3/cep/");
        when(apiInfo.getToken()).thenReturn("6fa935c35e88f2e15a9e5330493e5645");

        finder = new CEPAbertoProvider(apiInfo, getObjectMapper());
    }

    @Test
    public void findTest() throws ZipcodeInvalidException, ApiRequestException {
        String zipcode = "74393250";
        IAddress address = finder.find(zipcode);

        assertThat(address).isNotNull();
        assertThat(address.getCep()).isEqualTo(zipcode);
        assertThat(address.getLogradouro()).isEqualTo("Rua FP 27");
        assertThat(address.getBairro()).isEqualTo("Recreio do Funcionário Público");
        assertThat(address.getCidade()).isEqualTo("Goiânia");
        assertThat(address.getEstado()).isEqualTo("GO");
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
}

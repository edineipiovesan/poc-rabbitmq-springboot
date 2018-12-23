package com.edn.poc.rabbitmq.server.finder.impl;

import com.edn.poc.rabbitmq.server.configuration.api.impl.CEPAbertoApiInfo;
import com.edn.poc.rabbitmq.server.exception.ZipcodeInvalidException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeNotFoundException;
import com.edn.poc.rabbitmq.server.model.IAddress;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.edn.poc.rabbitmq.server.util.ComponentUtils.getObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CEPAbertoFinderTest {
    @Mock
    private CEPAbertoApiInfo apiInfo;

    private CEPAbertoFinder finder;

    @Before
    public void init() {
        when(apiInfo.getUrl()).thenReturn("http://www.cepaberto.com/");
        when(apiInfo.getEndpoint()).thenReturn("api/v3/cep/");
        when(apiInfo.getToken()).thenReturn("6fa935c35e88f2e15a9e5330493e5645");

        finder = new CEPAbertoFinder(apiInfo, getObjectMapper());
    }

    @Test
    public void findTest() throws ZipcodeNotFoundException, ZipcodeInvalidException {
        String zipcode = "74393250";
        IAddress address = finder.find(zipcode);

        assertThat(address).isNotNull();
        assertThat(address.getCep()).isEqualTo(zipcode);
        assertThat(address.getLogradouro()).isEqualTo("Rua FP 27");
        assertThat(address.getBairro()).isEqualTo("Recreio do Funcionário Público");
        assertThat(address.getCidade()).isEqualTo("Goiânia");
        assertThat(address.getEstado()).isEqualTo("GO");
    }

    @Test
    public void validZipcodeOnlyNumber() throws ZipcodeInvalidException, ZipcodeNotFoundException {
        String zipcodeOnlyNumber = "74393250";

        IAddress address = finder.find(zipcodeOnlyNumber);
        assertThat(address).isNotNull();
        assertThat(address.getCep()).isEqualTo(zipcodeOnlyNumber);
        assertThat(address.getLogradouro()).isEqualTo("Rua FP 27");
        assertThat(address.getBairro()).isEqualTo("Recreio do Funcionário Público");
        assertThat(address.getCidade()).isEqualTo("Goiânia");
        assertThat(address.getEstado()).isEqualTo("GO");
    }

    @Test
    public void validZipcodeWithHyphen() throws ZipcodeInvalidException, ZipcodeNotFoundException {
        String zipcodeWithHyphen = "74393-250";

        IAddress address = finder.find(zipcodeWithHyphen);
        assertThat(address).isNotNull();
        assertThat(address.getCep()).isEqualTo("74393250");
        assertThat(address.getLogradouro()).isEqualTo("Rua FP 27");
        assertThat(address.getBairro()).isEqualTo("Recreio do Funcionário Público");
        assertThat(address.getCidade()).isEqualTo("Goiânia");
        assertThat(address.getEstado()).isEqualTo("GO");
    }

    @Test(expected = ZipcodeInvalidException.class)
    public void zipcodeNullTest() throws ZipcodeNotFoundException, ZipcodeInvalidException {
        finder.find(null);
    }

    @Test(expected = ZipcodeInvalidException.class)
    public void zipcodeEmptyStringTest() throws ZipcodeNotFoundException, ZipcodeInvalidException {
        finder.find("");
    }

    @Test(expected = ZipcodeInvalidException.class)
    public void invalidZipcodeWithLetters() throws ZipcodeInvalidException, ZipcodeNotFoundException {
        finder.find("AAA393250");
    }

    @Test(expected = ZipcodeInvalidException.class)
    public void invalidZipcodeWith5Digits() throws ZipcodeInvalidException, ZipcodeNotFoundException {
        finder.find("74393");
    }
}

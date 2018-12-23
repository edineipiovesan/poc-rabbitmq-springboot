package com.edn.poc.rabbitmq.server.finder.impl;

import com.edn.poc.rabbitmq.server.configuration.api.impl.ViaCEPApiInfo;
import com.edn.poc.rabbitmq.server.exception.ZipcodeInvalidException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeNotFoundException;
import com.edn.poc.rabbitmq.server.model.IAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ViaCEPFinderTest {
    @Mock
    private ViaCEPApiInfo apiInfo;

    private ViaCEPFinder finder;

    @Before
    public void init() {
        when(apiInfo.getUrl()).thenReturn("https://viacep.com.br/");
        when(apiInfo.getEndpoint()).thenReturn("ws/");
        when(apiInfo.getFormat()).thenReturn("json");

        finder = new ViaCEPFinder(apiInfo, new ObjectMapper());
    }

    @Test
    public void findTest() throws ZipcodeNotFoundException, ZipcodeInvalidException {
        String zipcode = "74393250";
        IAddress address = finder.find(zipcode);

        assertThat(address).isNotNull();
        assertThat(address.getCep()).isEqualTo("74393-250");
        assertThat(address.getLogradouro()).isEqualTo("Rua FP27");
        assertThat(address.getBairro()).isEqualTo("Recreio do Funcionário Público");
        assertThat(address.getCidade()).isEqualTo("Goiânia");
        assertThat(address.getEstado()).isEqualTo("GO");
    }

    @Test
    public void validZipcodeOnlyNumber() throws ZipcodeInvalidException, ZipcodeNotFoundException {
        String zipcodeOnlyNumber = "74393250";
        IAddress address = finder.find(zipcodeOnlyNumber);

        assertThat(address).isNotNull();
        assertThat(address.getCep()).isEqualTo("74393-250");
        assertThat(address.getLogradouro()).isEqualTo("Rua FP27");
        assertThat(address.getBairro()).isEqualTo("Recreio do Funcionário Público");
        assertThat(address.getCidade()).isEqualTo("Goiânia");
        assertThat(address.getEstado()).isEqualTo("GO");
    }

    @Test
    public void validZipcodeWithHyphen() throws ZipcodeInvalidException, ZipcodeNotFoundException {
        String zipcodeWithHyphen = "74393-250";
        IAddress address = finder.find(zipcodeWithHyphen);

        assertThat(address).isNotNull();
        assertThat(address.getCep()).isEqualTo(zipcodeWithHyphen);
        assertThat(address.getLogradouro()).isEqualTo("Rua FP27");
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

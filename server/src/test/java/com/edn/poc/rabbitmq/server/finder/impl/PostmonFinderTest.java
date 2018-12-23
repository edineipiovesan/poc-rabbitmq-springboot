package com.edn.poc.rabbitmq.server.finder.impl;

import com.edn.poc.rabbitmq.server.configuration.api.impl.PostmonApiInfo;
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
public class PostmonFinderTest {

    @Mock
    private PostmonApiInfo apiInfo;

    private PostmonFinder finder;

    @Before
    public void init() {
        when(apiInfo.getUrl()).thenReturn("https://api.postmon.com.br/");
        when(apiInfo.getEndpoint()).thenReturn("v1/cep/");

        finder = new PostmonFinder(apiInfo, new ObjectMapper());
    }

    @Test
    public void findTest() throws ZipcodeNotFoundException, ZipcodeInvalidException {
        String zipcode = "74393250";
        IAddress address = finder.find(zipcode);

        assertThat(address).isNotNull();
        assertThat(address.getCep()).isEqualTo(zipcode);
        assertThat(address.getLogradouro()).isEqualTo("Rua FP27");
        assertThat(address.getBairro()).isEqualTo("Recreio do Funcionário Público");
        assertThat(address.getCidade()).isEqualTo("Goiânia");
        assertThat(address.getEstado()).isEqualTo("Goiás");
    }

    @Test
    public void validZipcodeOnlyNumber() throws ZipcodeInvalidException, ZipcodeNotFoundException {
        String zipcodeOnlyNumber = "74393250";
        IAddress address = finder.find(zipcodeOnlyNumber);

        assertThat(address).isNotNull();
        assertThat(address.getCep()).isEqualTo(zipcodeOnlyNumber);
        assertThat(address.getLogradouro()).isEqualTo("Rua FP27");
        assertThat(address.getBairro()).isEqualTo("Recreio do Funcionário Público");
        assertThat(address.getCidade()).isEqualTo("Goiânia");
        assertThat(address.getEstado()).isEqualTo("Goiás");
    }

    @Test
    public void validZipcodeWithHyphen() throws ZipcodeInvalidException, ZipcodeNotFoundException {
        String zipcodeWithHyphen = "74393-250";
        IAddress address = finder.find(zipcodeWithHyphen);

        assertThat(address).isNotNull();
        assertThat(address.getCep()).isEqualTo("74393250");
        assertThat(address.getLogradouro()).isEqualTo("Rua FP27");
        assertThat(address.getBairro()).isEqualTo("Recreio do Funcionário Público");
        assertThat(address.getCidade()).isEqualTo("Goiânia");
        assertThat(address.getEstado()).isEqualTo("Goiás");
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

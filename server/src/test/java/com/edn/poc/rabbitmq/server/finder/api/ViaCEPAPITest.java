package com.edn.poc.rabbitmq.server.finder.api;

import com.edn.poc.rabbitmq.server.configuration.api.ApiInfo;
import com.edn.poc.rabbitmq.server.exception.ApiRequestException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeInvalidException;
import com.edn.poc.rabbitmq.server.finder.ZipcodeFinder;
import com.edn.poc.rabbitmq.server.finder.impl.ViaCEPFinder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.edn.poc.rabbitmq.server.util.ComponentUtils.getObjectMapper;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ViaCEPAPITest {
    @Mock
    private ApiInfo apiInfo;

    private ZipcodeFinder finder;

    @Test(expected = ApiRequestException.class)
    public void badUrlTest() throws ZipcodeInvalidException, ApiRequestException {
        when(apiInfo.getUrl()).thenReturn("https://viacep.com/");
        when(apiInfo.getEndpoint()).thenReturn("ws/");
        when(apiInfo.getFormat()).thenReturn("json");

        finder = new ViaCEPFinder(apiInfo, getObjectMapper());
        finder.find("74393250");
    }

    @Test(expected = ApiRequestException.class)
    public void badEndpointTest() throws ZipcodeInvalidException, ApiRequestException {
        when(apiInfo.getUrl()).thenReturn("https://viacep.com.br/");
        when(apiInfo.getEndpoint()).thenReturn("ws/error");
        when(apiInfo.getFormat()).thenReturn("json");

        finder = new ViaCEPFinder(apiInfo, getObjectMapper());
        finder.find("74393250");
    }

    @Test(expected = ApiRequestException.class)
    public void badFormatTest() throws ZipcodeInvalidException, ApiRequestException {
        when(apiInfo.getUrl()).thenReturn("https://viacep.com.br/");
        when(apiInfo.getEndpoint()).thenReturn("ws/");
        when(apiInfo.getFormat()).thenReturn("xml");

        finder = new ViaCEPFinder(apiInfo, getObjectMapper());
        finder.find("74393250");
    }
}

package com.edn.poc.rabbitmq.server.finder.api;

import com.edn.poc.rabbitmq.server.configuration.api.ApiInfo;
import com.edn.poc.rabbitmq.server.exception.ApiRequestException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeInvalidException;
import com.edn.poc.rabbitmq.server.finder.ZipcodeFinder;
import com.edn.poc.rabbitmq.server.finder.impl.CEPAbertoFinder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.edn.poc.rabbitmq.server.util.ComponentUtils.getObjectMapper;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CEPAbertoAPITest {

    @Mock
    private ApiInfo apiInfo;

    private ZipcodeFinder finder;

    @Test(expected = ApiRequestException.class)
    public void badUrlTest() throws ZipcodeInvalidException, ApiRequestException {
        when(apiInfo.getUrl()).thenReturn("http://www.cepaberto.com.br/");
        when(apiInfo.getEndpoint()).thenReturn("api/v3/cep/");
        when(apiInfo.getToken()).thenReturn("6fa935c35e88f2e15a9e5330493e5645");

        finder = new CEPAbertoFinder(apiInfo, getObjectMapper());
        finder.find("74393250");
    }

    @Test(expected = ApiRequestException.class)
    public void badEndpointTest() throws ZipcodeInvalidException, ApiRequestException {
        when(apiInfo.getUrl()).thenReturn("http://www.cepaberto.com/");
        when(apiInfo.getEndpoint()).thenReturn("api/v3/cep/error");
        when(apiInfo.getToken()).thenReturn("6fa935c35e88f2e15a9e5330493e5645");

        finder = new CEPAbertoFinder(apiInfo, getObjectMapper());
        finder.find("74393250");
    }

    @Test(expected = ApiRequestException.class)
    public void badTokenTest() throws ZipcodeInvalidException, ApiRequestException {
        when(apiInfo.getUrl()).thenReturn("http://www.cepaberto.com/");
        when(apiInfo.getEndpoint()).thenReturn("api/v3/cep/error");
        when(apiInfo.getToken()).thenReturn("6fa935c35e88f2e1asd30493e5645");

        finder = new CEPAbertoFinder(apiInfo, getObjectMapper());
        finder.find("74393250");
    }
}

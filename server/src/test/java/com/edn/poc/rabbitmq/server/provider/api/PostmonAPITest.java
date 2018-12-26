package com.edn.poc.rabbitmq.server.provider.api;

import com.edn.poc.rabbitmq.server.exception.ApiRequestException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeInvalidException;
import com.edn.poc.rabbitmq.server.provider.service.ZipcodeProvider;
import com.edn.poc.rabbitmq.server.provider.service.impl.PostmonProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.edn.poc.rabbitmq.server.util.ComponentUtils.getObjectMapper;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostmonAPITest {
    @Mock
    private ApiInfo apiInfo;

    private ZipcodeProvider finder;

    @Test(expected = ApiRequestException.class)
    public void badUrlTest() throws ZipcodeInvalidException, ApiRequestException {
        when(apiInfo.getUrl()).thenReturn("https://api.postmon.com/");
        when(apiInfo.getEndpoint()).thenReturn("v1/cep/");

        finder = new PostmonProvider(apiInfo, getObjectMapper());
        finder.find("74393250");
    }

    @Test(expected = ApiRequestException.class)
    public void badEndpointTest() throws ZipcodeInvalidException, ApiRequestException {
        when(apiInfo.getUrl()).thenReturn("https://api.postmon.com.br/");
        when(apiInfo.getEndpoint()).thenReturn("v1/cep/error");

        finder = new PostmonProvider(apiInfo, getObjectMapper());
        finder.find("74393250");
    }
}

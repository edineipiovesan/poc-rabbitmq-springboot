package com.edn.poc.rabbitmq.server.service;

import com.edn.poc.rabbitmq.server.configuration.api.impl.CEPAbertoApiInfo;
import com.edn.poc.rabbitmq.server.dto.response.IAddress;
import com.edn.poc.rabbitmq.server.exception.ZipcodeFinderException;
import com.edn.poc.rabbitmq.server.finder.impl.CEPAbertoZipcodeFinder;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@Log4j2
@RunWith(MockitoJUnitRunner.class)
public class CEPAbertoZipcodeFinderTest {

    @Mock
    private CEPAbertoApiInfo cepAbertoApiInfo;

    @InjectMocks
    private CEPAbertoZipcodeFinder CEPAbertoZipcodeFinder;

    @Before
    public void init() {
        when(cepAbertoApiInfo.getToken()).thenReturn("6fa935c35e88f2e15a9e5330493e5645");
        when(cepAbertoApiInfo.getUrl()).thenReturn("http://www.cepaberto.com/");
        when(cepAbertoApiInfo.getEndpoint()).thenReturn("api/v3/cep");
        when(cepAbertoApiInfo.getFormat()).thenReturn("json");
    }

    @Test
    public void findTest() throws ZipcodeFinderException {
        IAddress address = CEPAbertoZipcodeFinder.find("01311914");
        log.info("CEPAbertoAddress found is {}", address);
        assertThat(address).isNotNull();
    }

}

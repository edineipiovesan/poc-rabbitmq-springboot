package com.edn.poc.rabbitmq.server.service;

import com.edn.poc.rabbitmq.server.configuration.api.impl.CEPAbertoApiInfo;
import com.edn.poc.rabbitmq.server.exception.ZipcodeFinderException;
import com.edn.poc.rabbitmq.server.model.impl.CEPAbertoAddress;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@Log4j2
@RunWith(MockitoJUnitRunner.class)
public class CEPAbertoZipcodeFinderTest {

    @Mock
    private CEPAbertoApiInfo cepAbertoApiInfo;

    @InjectMocks
    private com.edn.poc.rabbitmq.server.finder.impl.CEPAbertoZipcodeFinder CEPAbertoZipcodeFinder;

    @Before
    public void init() throws IOException {
        when(cepAbertoApiInfo.getToken()).thenReturn("6fa935c35e88f2e15a9e5330493e5645");
        when(cepAbertoApiInfo.getUrl()).thenReturn("http://www.cepaberto.com/");
        when(cepAbertoApiInfo.getEndpoint()).thenReturn("api/v3/cep");
        when(cepAbertoApiInfo.getFormat()).thenReturn("json");
    }

    @Test
    public void findTest() throws ZipcodeFinderException {
        CEPAbertoAddress CEPAbertoAddress = CEPAbertoZipcodeFinder.find("01311914");
        log.info("CEPAbertoAddress found is {}", CEPAbertoAddress);
        assertThat(CEPAbertoAddress).isNotNull();
    }

}

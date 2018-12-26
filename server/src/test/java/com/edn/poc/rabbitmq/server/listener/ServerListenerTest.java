package com.edn.poc.rabbitmq.server.listener;

import com.edn.poc.rabbitmq.server.provider.ProviderRegister;
import com.edn.poc.rabbitmq.server.provider.api.impl.CEPAbertoApiInfo;
import com.edn.poc.rabbitmq.server.provider.api.impl.PostmonApiInfo;
import com.edn.poc.rabbitmq.server.provider.api.impl.ViaCEPApiInfo;
import com.edn.poc.rabbitmq.server.provider.model.IAddress;
import com.edn.poc.rabbitmq.server.provider.service.ZipcodeProvider;
import com.edn.poc.rabbitmq.server.provider.service.impl.CEPAbertoProvider;
import com.edn.poc.rabbitmq.server.provider.service.impl.PostmonProvider;
import com.edn.poc.rabbitmq.server.provider.service.impl.ViaCEPProvider;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.edn.poc.rabbitmq.server.util.ComponentUtils.getObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServerListenerTest {

    @Mock
    CEPAbertoApiInfo cepAbertoApiInfo;

    @Mock
    PostmonApiInfo postmonApiInfo;

    @Mock
    ViaCEPApiInfo viaCEPApiInfo;

    @Mock
    ProviderRegister providerRegister;

    private ServerListener serverListener;

    @Before
    public void init() {
        when(cepAbertoApiInfo.getUrl()).thenReturn("http://www.cepaberto.com/");
        when(cepAbertoApiInfo.getEndpoint()).thenReturn("api/v3/cep/");
        when(cepAbertoApiInfo.getToken()).thenReturn("6fa935c35e88f2e15a9e5330493e5645");
        CEPAbertoProvider cepAbertoFinder = new CEPAbertoProvider(cepAbertoApiInfo, getObjectMapper());

        when(postmonApiInfo.getUrl()).thenReturn("https://api.postmon.com.br/");
        when(postmonApiInfo.getEndpoint()).thenReturn("v1/cep/");
        PostmonProvider postmonFinder = new PostmonProvider(postmonApiInfo, getObjectMapper());

        when(viaCEPApiInfo.getUrl()).thenReturn("https://viacep.com.br/");
        when(viaCEPApiInfo.getEndpoint()).thenReturn("ws/");
        when(viaCEPApiInfo.getFormat()).thenReturn("json");
        ViaCEPProvider viaCEPFinder = new ViaCEPProvider(viaCEPApiInfo, getObjectMapper());

        Set<ZipcodeProvider> providers = new HashSet<>();
        providers.add(cepAbertoFinder);
        providers.add(postmonFinder);
        providers.add(viaCEPFinder);
        Iterator<ZipcodeProvider> iterator = Iterables.cycle(providers).iterator();

        when(providerRegister.getProvider()).thenAnswer((Answer<ZipcodeProvider>) invocation -> iterator.next());
        when(providerRegister.getTotalProviders()).thenReturn(providers.size());

        serverListener = new ServerListener(providerRegister);
    }

    @Test
    public void listenerTest() {
        List<String> zipcodes = Arrays.asList("74393250", "74170-030", "74223060", "74810-907", "04801-010");

        IAddress address;
        for (String zipcode : zipcodes) {
            address = serverListener.decodeZipCode(zipcode);
            assertThat(address).isNotNull();
        }
    }

    @Test(expected = AmqpRejectAndDontRequeueException.class)
    public void invalidZipcodeTest() {
        IAddress address = serverListener.decodeZipCode("74000000");
        assertThat(address).isNotNull();
    }

    @Test
    public void roundRobinTest() {
        ZipcodeProvider[] providers = new ZipcodeProvider[6];
        for (int i = 0; i < 6; i++) {
            providers[i] = providerRegister.getProvider();
        }

        assertThat(providers[0].getApiName()).isEqualTo(providers[3].getApiName());
        assertThat(providers[1].getApiName()).isEqualTo(providers[4].getApiName());
        assertThat(providers[2].getApiName()).isEqualTo(providers[5].getApiName());
    }
}

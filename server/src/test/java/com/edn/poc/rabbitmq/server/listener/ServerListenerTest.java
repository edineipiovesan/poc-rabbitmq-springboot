package com.edn.poc.rabbitmq.server.listener;

import com.edn.poc.rabbitmq.server.provider.api.impl.CEPAbertoApiInfo;
import com.edn.poc.rabbitmq.server.provider.api.impl.PostmonApiInfo;
import com.edn.poc.rabbitmq.server.provider.api.impl.ViaCEPApiInfo;
import com.edn.poc.rabbitmq.server.provider.service.ZipcodeProvider;
import com.edn.poc.rabbitmq.server.provider.service.impl.CEPAbertoProvider;
import com.edn.poc.rabbitmq.server.provider.service.impl.PostmonProvider;
import com.edn.poc.rabbitmq.server.provider.service.impl.ViaCEPProvider;
import com.edn.poc.rabbitmq.server.provider.model.IAddress;
import com.edn.poc.rabbitmq.server.provider.ProviderRegister;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multiset;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;

import java.util.HashSet;
import java.util.Iterator;
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
    private CircularFifoQueue<ZipcodeProvider> providers;
    private Iterator<ZipcodeProvider> iterator;

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


        providers = new CircularFifoQueue<>();
        providers.add(cepAbertoFinder);
        providers.add(postmonFinder);
        providers.add(viaCEPFinder);
        iterator = Iterables.cycle(providers).iterator();

        when(providerRegister.getProvider()).thenReturn(roundRobinProvider());

        serverListener = new ServerListener(providerRegister);
    }

    private ZipcodeProvider roundRobinProvider() {
        ZipcodeProvider provider = providers.remove();
        providers.add(provider);
        return provider;
    }

    @Test
    public void listenerTest() {
        IAddress address = serverListener.decodeZipCode("74393250");
        assertThat(address).isNotNull();
    }

    @Test(expected = AmqpRejectAndDontRequeueException.class)
    public void invalidZipcodeTest()  {
        IAddress address = serverListener.decodeZipCode("74000000");
        assertThat(address).isNotNull();
    }

    @Test
    public void roundRobinTest() {
        ZipcodeProvider provider1 = providerRegister.getProvider();
        System.out.println(provider1.getApiName());

        ZipcodeProvider provider2 = providerRegister.getProvider();
        System.out.println(provider2.getApiName());

        ZipcodeProvider provider3 = providerRegister.getProvider();
        System.out.println(provider3.getApiName());

        ZipcodeProvider provider4 = providerRegister.getProvider();
        System.out.println(provider4.getApiName());

        ZipcodeProvider provider5 = providerRegister.getProvider();
        System.out.println(provider5.getApiName());

        ZipcodeProvider provider6 = providerRegister.getProvider();
        System.out.println(provider6.getApiName());

        assertThat(provider1.getApiName()).isEqualTo(provider4.getApiName());
        assertThat(provider2.getApiName()).isEqualTo(provider5.getApiName());
        assertThat(provider3.getApiName()).isEqualTo(provider6.getApiName());
    }
}

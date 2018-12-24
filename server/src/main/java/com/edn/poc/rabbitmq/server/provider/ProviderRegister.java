package com.edn.poc.rabbitmq.server.provider;

import com.edn.poc.rabbitmq.server.provider.service.ZipcodeProvider;
import com.google.common.collect.Iterables;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Configuration
@Log4j2
public class ProviderRegister {

    final ApplicationContext context;
    private Set<ZipcodeProvider> providers;
    private Iterator<ZipcodeProvider> iterator;

    @Autowired
    public ProviderRegister(ApplicationContext context) {
        this.context = context;
    }

    public Set<ZipcodeProvider> getProviders() {
        return providers;
    }

    public ZipcodeProvider getProvider() {
        return iterator.next();
    }

    public int getTotalProviders() {
        return providers.size();
    }

    @Bean
    public void registerProviders() {
        providers = new HashSet<>(context.getBeansOfType(ZipcodeProvider.class).values());
        iterator = Iterables.cycle(providers).iterator();
    }

}

package com.edn.poc.rabbitmq.server.listener;

import com.edn.poc.rabbitmq.server.exception.ApiRequestException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeInvalidException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeNotFoundException;
import com.edn.poc.rabbitmq.server.provider.ProviderRegister;
import com.edn.poc.rabbitmq.server.provider.model.IAddress;
import com.edn.poc.rabbitmq.server.provider.service.ZipcodeProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ServerListener {

    private final ProviderRegister providerRegister;

    @Autowired
    public ServerListener(ProviderRegister providerRegister) {
        this.providerRegister = providerRegister;
    }

    @RabbitListener(queues = "${mq.queue.request}")
    public IAddress decodeZipCode(String zipcode) {
        log.info("[x] Received zipcode {}", zipcode);

        try {
            IAddress address = findUsingAnyProvider(zipcode);
            log.info("Returning {}", address);
            return address;
        } catch (ZipcodeInvalidException e) {
            log.error("Can not process the request: {}", e.getMessage());
        } catch (ZipcodeNotFoundException e) {
            log.warn("Zipcode not found: {}", e.getMessage());
        }

        log.warn("Sending to dead-letter queue");
        throw new AmqpRejectAndDontRequeueException("Sending to dead-letter queue");
    }

    private IAddress findUsingAnyProvider(String zipcode) throws ZipcodeInvalidException, ZipcodeNotFoundException {
        ZipcodeProvider provider;

        int totalProviders = providerRegister.getTotalProviders();
        for (int i = 0; i < totalProviders; i++) {
            provider = providerRegister.getProvider();
            log.info("Provider from roundrobin is {}", provider.getApiName());
            try {
                IAddress address = provider.find(zipcode);
                log.info("Zipcode {} found by {}", zipcode, provider.getApiName());
                return address;
            } catch (ApiRequestException e) {
                log.warn("Provider {} cannot request zipcode {}", provider.getClass().getSimpleName(), zipcode);
            }
        }

        throw new ZipcodeNotFoundException("Zipcode is not valid or none of our providers can find it at the moment");
    }
}

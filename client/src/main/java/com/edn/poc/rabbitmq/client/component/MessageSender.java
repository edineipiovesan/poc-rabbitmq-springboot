package com.edn.poc.rabbitmq.client.component;

import com.edn.poc.rabbitmq.client.configuration.ApplicationConfig;
import com.edn.poc.rabbitmq.client.exception.ZipCodeGeneratorException;
import com.edn.poc.rabbitmq.client.model.Address;
import com.edn.poc.rabbitmq.client.service.ZipCodeGeneratorService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private ZipCodeGeneratorService zipCodeGeneratorService;

    @Scheduled(fixedRate = 10000)
    public void sendObject() throws ZipCodeGeneratorException {
        String exchange = applicationConfig.getExchange();
        String routingKey = applicationConfig.getRoutingKey();

        String zipcode = zipCodeGeneratorService.getZipcode();
        Address address = rabbitTemplate.convertSendAndReceiveAsType(exchange, routingKey, zipcode, new ParameterizedTypeReference<Address>() {
        });

        System.out.println("[x] Sent " + zipcode);
        System.out.println("[.] Received " + address);
    }

}

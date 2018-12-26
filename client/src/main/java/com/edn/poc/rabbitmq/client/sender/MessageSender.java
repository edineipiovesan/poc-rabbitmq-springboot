package com.edn.poc.rabbitmq.client.sender;

import com.edn.poc.rabbitmq.client.configuration.ApplicationConfig;
import com.edn.poc.rabbitmq.client.exception.ZipCodeGeneratorException;
import com.edn.poc.rabbitmq.client.model.Address;
import com.edn.poc.rabbitmq.client.service.ZipCodeGeneratorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class MessageSender {

    private final RabbitTemplate rabbitTemplate;
    private final ApplicationConfig applicationConfig;
    private final ZipCodeGeneratorService zipCodeGeneratorService;

    @Autowired
    public MessageSender(RabbitTemplate rabbitTemplate, ApplicationConfig applicationConfig, ZipCodeGeneratorService zipCodeGeneratorService) {
        this.rabbitTemplate = rabbitTemplate;
        this.applicationConfig = applicationConfig;
        this.zipCodeGeneratorService = zipCodeGeneratorService;
    }

    @Scheduled(fixedRate = 10000)
    public void sendObject() throws ZipCodeGeneratorException {
        String exchange = applicationConfig.getExchange();
        String routingKey = applicationConfig.getRoutingKey();

        String zipcode = zipCodeGeneratorService.getZipcode();
        Address address = rabbitTemplate.convertSendAndReceiveAsType(exchange, routingKey, zipcode,
                new ParameterizedTypeReference<Address>() {
                });

        log.info("[x] Sent {}", zipcode);
        log.info("[.] Received {}", address);
    }

}

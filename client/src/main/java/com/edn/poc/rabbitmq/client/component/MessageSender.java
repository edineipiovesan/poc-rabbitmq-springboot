package com.edn.poc.rabbitmq.client.component;

import com.edn.poc.rabbitmq.client.configuration.ApplicationConfig;
import com.edn.poc.rabbitmq.client.model.MessageModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Scheduled(fixedRate = 10)
    public void sendObject() {
        String exchange = applicationConfig.getExchange();
        String routingKey = applicationConfig.getRoutingKey();

        String id = UUID.randomUUID().toString().toLowerCase();
        String name = UUID.randomUUID().toString().toLowerCase();
        Long number = (long) (100000 + new Random().nextInt(900000));
        MessageModel sendObject = new MessageModel(id, name, number);

        MessageModel model = rabbitTemplate.convertSendAndReceiveAsType(exchange, routingKey, sendObject, new ParameterizedTypeReference<MessageModel>() {});

        System.out.println(String.format("[x] Sent: %s", name));
        System.out.println(String.format("[.] Received: %s", model.getName()));
    }

}

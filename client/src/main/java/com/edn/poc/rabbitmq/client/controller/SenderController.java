package com.edn.poc.rabbitmq.client.controller;

import com.edn.poc.rabbitmq.client.configuration.ApplicationConfig;
import com.edn.poc.rabbitmq.client.model.MessageModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/send")
public class SenderController {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public String sendMessage(@RequestParam("msg") String msg, @RequestParam(value = "repeat", defaultValue = "1") Long repeat) {
        String exchange = applicationConfig.getExchange();
        String routingKey = applicationConfig.getRoutingKey();

        MessageModel messageModel = new MessageModel();
        messageModel.setId(1L);
        messageModel.setName(msg);

        for (int i = 0; i < repeat; i++) {
            messageModel.setId((long) i);
            messageModel.setName(UUID.randomUUID().toString().toLowerCase());
            rabbitTemplate.convertAndSend(exchange, routingKey, messageModel);
        }

        return String.format("Message %s was sent %d times.", msg, repeat);
    }
}

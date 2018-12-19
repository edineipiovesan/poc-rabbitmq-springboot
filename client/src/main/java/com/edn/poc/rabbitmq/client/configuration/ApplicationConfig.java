package com.edn.poc.rabbitmq.client.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Getter @Setter
public class ApplicationConfig {
    @Value("${mq.exchange.name}")
    private String exchange;

    @Value("${mq.queue.request}")
    private String queueRequest;

    @Value("${mq.routing.key}")
    private String routingKey;
}

package com.edn.poc.rabbitmq.server.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
    @Value("${mq.exchange.name}")
    private String exchange;

    @Value("${mq.queue.request}")
    private String incomingQueue;

    @Value("${mq.routing.key}")
    private String routingKey;

    @Value("${mq.queue.dead-letter}")
    private String deadLetterQueue;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getIncomingQueue() {
        return incomingQueue;
    }

    public void setIncomingQueue(String incomingQueue) {
        this.incomingQueue = incomingQueue;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getDeadLetterQueue() {
        return deadLetterQueue;
    }

    public void setDeadLetterQueue(String deadLetterQueue) {
        this.deadLetterQueue = deadLetterQueue;
    }
}

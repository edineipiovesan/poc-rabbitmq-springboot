package com.edn.poc.rabbitmq.client.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
    @Value("${mq.exchange.name}")
    private String exchange;

    @Value("${mq.queue.request}")
    private String queueRequest;

    @Value("${mq.routing.key}")
    private String routingKey;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getQueueRequest() {
        return queueRequest;
    }

    public void setQueueRequest(String queueRequest) {
        this.queueRequest = queueRequest;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
}

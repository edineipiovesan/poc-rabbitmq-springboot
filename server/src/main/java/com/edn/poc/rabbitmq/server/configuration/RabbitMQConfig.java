package com.edn.poc.rabbitmq.server.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

/**
 * Server application create exchange, queue and bind them.
 * Client application should only connect to exchange.
 */
@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(applicationConfig.getExchange());
    }

    @Bean
    public Queue queue() {
        return new Queue(applicationConfig.getQueueRequest());
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(applicationConfig.getRoutingKey());
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

}

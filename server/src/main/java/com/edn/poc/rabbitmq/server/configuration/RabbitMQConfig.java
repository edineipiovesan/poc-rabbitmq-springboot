package com.edn.poc.rabbitmq.server.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    private final ApplicationConfig applicationConfig;

    @Autowired
    public RabbitMQConfig(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(applicationConfig.getExchange());
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(applicationConfig.getDeadLetterQueue()).build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(applicationConfig.getIncomingQueue())
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", applicationConfig.getDeadLetterQueue())
                .build();
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

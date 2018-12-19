package com.edn.poc.rabbitmq.server.listener;

import com.edn.poc.rabbitmq.server.model.MessageModel;
import com.edn.poc.rabbitmq.server.model.ReplyMessageModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Log4j2
public class ServerListener {

//    @RabbitListener(queues = "${mq.queue.request}")
//    public void toUpperCase(String message) throws InterruptedException {
//        log.info(" [x] Received String request: " + message);
//
//        // Random delay between 0 and 200ms
//        int sleep = new Random().nextInt(200);
//        log.info("Sleeping for {}ms", sleep);
//        Thread.sleep(sleep);
//    }

    @RabbitListener(queues = "${mq.queue.request}")
    public ReplyMessageModel toUpperCase(MessageModel message) throws InterruptedException {
        System.out.println(" [x] Received object request: " + message);

        // Random delay between 0 and 200ms
        int sleep = new Random().nextInt(200);
        System.out.println("Sleeping for " + sleep + "ms");
        Thread.sleep(sleep);

        return new ReplyMessageModel(message.getId(), message.getName().toUpperCase(), message.getNumber());
    }
}

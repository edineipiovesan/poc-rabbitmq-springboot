package com.edn.poc.rabbitmq.server.listener;

import com.edn.poc.rabbitmq.server.model.MessageModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Log4j2
public class ServerListener {

    @RabbitListener(queues = "${mq.queue.request}")
    public void toUpperCase(MessageModel messageModel) throws InterruptedException {
        log.info(" [x] Received request for " + messageModel);

        // Random delay between 0 and 200ms
        int sleep = new Random().nextInt(200);
        log.info("Sleeping for {}ms", sleep);
        Thread.sleep(sleep);

        messageModel.setName(messageModel.getName().toUpperCase());
        log.info(" [.] Returned " + messageModel);
    }
}

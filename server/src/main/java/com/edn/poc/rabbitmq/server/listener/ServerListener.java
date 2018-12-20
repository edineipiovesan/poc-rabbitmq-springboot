package com.edn.poc.rabbitmq.server.listener;

import com.edn.poc.rabbitmq.server.exception.ZipCodeException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeNotFoundException;
import com.edn.poc.rabbitmq.server.finder.ZipcodeFinder;
import com.edn.poc.rabbitmq.server.model.BasicAddress;
import com.edn.poc.rabbitmq.server.service.CEPAbertoZipcodeFinder;
import com.edn.poc.rabbitmq.server.service.PostmonZipcodeFinder;
import com.edn.poc.rabbitmq.server.service.ViaCEPZipcodeFinder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerListener {

    @Autowired
    private ViaCEPZipcodeFinder viaCEPZipcodeFinder;

    @Autowired
    private PostmonZipcodeFinder postmonZipcodeFinder;

    @Autowired
    private CEPAbertoZipcodeFinder cepAbertoZipcodeFinder;

    @RabbitListener(queues = "${mq.queue.request}")
    public BasicAddress decodeZipCode(String zipcode) throws ZipcodeNotFoundException {
        System.out.println(" [x] Received zipcode " + zipcode);
        return findUsingProvider(viaCEPZipcodeFinder, zipcode);
    }

    public BasicAddress findUsingProvider(ZipcodeFinder zipcodeFinder, String zipcode) throws ZipcodeNotFoundException {
        try {
            return zipcodeFinder.find(zipcode);
        } catch (ZipCodeException e) {
            throw new ZipcodeNotFoundException("Zipcode not found!");
        }
    }
}

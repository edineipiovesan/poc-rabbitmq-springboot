package com.edn.poc.rabbitmq.server.listener;

import com.edn.poc.rabbitmq.server.exception.ZipcodeInvalidException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeNotFoundException;
import com.edn.poc.rabbitmq.server.finder.ZipcodeFinder;
import com.edn.poc.rabbitmq.server.finder.impl.CEPAbertoFinder;
import com.edn.poc.rabbitmq.server.finder.impl.PostmonFinder;
import com.edn.poc.rabbitmq.server.finder.impl.ViaCEPFinder;
import com.edn.poc.rabbitmq.server.model.IAddress;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class ServerListener {

    private final ViaCEPFinder viaCEPFinder;

    private final PostmonFinder postmonFinder;

    private final CEPAbertoFinder cepAbertoFinder;

    @Autowired
    public ServerListener(ViaCEPFinder viaCEPFinder, PostmonFinder postmonFinder, CEPAbertoFinder cepAbertoFinder) {
        this.viaCEPFinder = viaCEPFinder;
        this.postmonFinder = postmonFinder;
        this.cepAbertoFinder = cepAbertoFinder;
    }

    @RabbitListener(queues = "${mq.queue.request}")
    public IAddress decodeZipCode(String zipcode) {
        System.out.println(" [x] Received zipcode " + zipcode);

        try {
            return findUsingAnyProvider(zipcode);
        } catch (ZipcodeNotFoundException e) {
            throw new AmqpRejectAndDontRequeueException("Sending to dead-letter queue");
        }
    }

    private IAddress findUsingAnyProvider(String zipcode) throws ZipcodeNotFoundException {
        IAddress address = null;

        Set<ZipcodeFinder> providers = new HashSet<>();
        providers.add(cepAbertoFinder);
        providers.add(viaCEPFinder);
        providers.add(postmonFinder);

        for (ZipcodeFinder finder : providers) {
            try {
                address = finder.find(zipcode);
                if (Objects.nonNull(address)) {
                    System.out.println(address);
                    return address;
                }
            } catch (ZipcodeNotFoundException e) {
                System.out.println(String.format("Provider %s cannot request zipcode %s.", finder.getClass().getSimpleName(), zipcode));
            } catch (ZipcodeInvalidException e) {
                e.printStackTrace();
            }
        }

        throw new ZipcodeNotFoundException("None of our providers can request this zipcode at the moment.");
    }
}

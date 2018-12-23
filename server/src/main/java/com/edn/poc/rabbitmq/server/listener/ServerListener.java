package com.edn.poc.rabbitmq.server.listener;

import com.edn.poc.rabbitmq.server.exception.ZipcodeInvalidException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeNotFoundException;
import com.edn.poc.rabbitmq.server.finder.ZipcodeFinder;
import com.edn.poc.rabbitmq.server.finder.impl.CEPAbertoFinder;
import com.edn.poc.rabbitmq.server.finder.impl.PostmonFinder;
import com.edn.poc.rabbitmq.server.finder.impl.ViaCEPFinder;
import com.edn.poc.rabbitmq.server.model.IAddress;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static com.edn.poc.rabbitmq.server.util.StandardUtils.standardizeZipcode;

@Component
@Log4j2
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
        log.info("[x] Received zipcode {}", zipcode);

        try {
            IAddress address = findUsingAnyProvider(zipcode);
            log.info("Returning {}", address);
            return address;
        } catch (ZipcodeNotFoundException | ZipcodeInvalidException e) {
            log.error("Can not process the request: {}", e.getMessage());
            log.info("Sending to dead-letter queue");
            throw new AmqpRejectAndDontRequeueException("Sending to dead-letter queue");
        }
    }

    private IAddress findUsingAnyProvider(String zipcode) throws ZipcodeNotFoundException, ZipcodeInvalidException {
        String standardizedZipcode = standardizeZipcode(zipcode);

        Set<ZipcodeFinder> providers = new HashSet<>();
        providers.add(cepAbertoFinder);
        providers.add(viaCEPFinder);
        providers.add(postmonFinder);

        for (ZipcodeFinder finder : providers) {
            try {
                IAddress address = finder.find(standardizedZipcode);
                log.info("Zipcode {} found by {}", zipcode, finder.getApiName());
                return address;
            } catch (ZipcodeNotFoundException e) {
                log.warn("Provider {} cannot request zipcode {}", finder.getClass().getSimpleName(), zipcode);
            }
        }

        throw new ZipcodeNotFoundException("None of our providers can request this zipcode at the moment");
    }
}

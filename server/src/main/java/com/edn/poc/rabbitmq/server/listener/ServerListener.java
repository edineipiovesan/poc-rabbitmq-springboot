package com.edn.poc.rabbitmq.server.listener;

import com.edn.poc.rabbitmq.server.dto.response.AddressResponse;
import com.edn.poc.rabbitmq.server.exception.ZipcodeFinderException;
import com.edn.poc.rabbitmq.server.exception.ZipcodeNotFoundException;
import com.edn.poc.rabbitmq.server.finder.ZipcodeFinder;
import com.edn.poc.rabbitmq.server.finder.impl.CEPAbertoZipcodeFinder;
import com.edn.poc.rabbitmq.server.finder.impl.PostmonZipcodeFinder;
import com.edn.poc.rabbitmq.server.finder.impl.ViaCEPZipcodeFinder;
import com.edn.poc.rabbitmq.server.model.BaseAddress;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class ServerListener {

    @Autowired
    private ViaCEPZipcodeFinder viaCEPZipcodeFinder;

    @Autowired
    private PostmonZipcodeFinder postmonZipcodeFinder;

    @Autowired
    private CEPAbertoZipcodeFinder cepAbertoZipcodeFinder;

    @RabbitListener(queues = "${mq.queue.request}")
    public AddressResponse decodeZipCode(String zipcode) throws ZipcodeNotFoundException {
        System.out.println(" [x] Received zipcode " + zipcode);
        return new AddressResponse(findUsingAnyProvider(zipcode));
    }

    private BaseAddress findUsingAnyProvider(String zipcode) throws ZipcodeNotFoundException {
        BaseAddress address = null;

        Set<ZipcodeFinder> providers = new HashSet<>();
        providers.add(cepAbertoZipcodeFinder);
        providers.add(postmonZipcodeFinder);
        providers.add(viaCEPZipcodeFinder);

        for (ZipcodeFinder finder : providers) {
            try {
                address = finder.find(zipcode);
                if (Objects.nonNull(address)) {
                    System.out.println(address);
                    return address;
                }
            } catch (ZipcodeFinderException e) {
                System.out.println(String.format("Provider %s cannot find zipcode %s.", finder.getClass().getSimpleName(), zipcode));
            }
        }

        throw new ZipcodeNotFoundException("None of our providers can find this zipcode at the moment.");
    }
}

package com.edn.poc.rabbitmq.server.service;

import com.edn.poc.rabbitmq.server.exception.ZipCodeException;
import com.edn.poc.rabbitmq.server.finder.ZipcodeFinder;
import com.edn.poc.rabbitmq.server.model.BasicAddress;
import org.springframework.stereotype.Service;

@Service
public class PostmonZipcodeFinder implements ZipcodeFinder {
    @Override
    public BasicAddress find(String zipcode) throws ZipCodeException {
        return null;
    }
}

package com.edn.poc.rabbitmq.server.finder.impl;

import com.edn.poc.rabbitmq.server.finder.ZipcodeFinder;
import com.edn.poc.rabbitmq.server.model.impl.PostmonAddress;

public class PostmonZipcodeFinder implements ZipcodeFinder {
    @Override
    public PostmonAddress find(String zipcode) {
        return null;
    }
}

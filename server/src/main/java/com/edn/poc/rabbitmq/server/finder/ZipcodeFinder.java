package com.edn.poc.rabbitmq.server.finder;

import com.edn.poc.rabbitmq.server.exception.ZipCodeException;
import com.edn.poc.rabbitmq.server.model.BasicAddress;

public interface ZipcodeFinder {

    BasicAddress find(String zipcode) throws ZipCodeException;
}

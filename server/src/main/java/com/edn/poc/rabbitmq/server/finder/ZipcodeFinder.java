package com.edn.poc.rabbitmq.server.finder;

import com.edn.poc.rabbitmq.server.exception.ZipcodeFinderException;
import com.edn.poc.rabbitmq.server.model.BaseAddress;

public interface ZipcodeFinder {

    BaseAddress find(String zipcode) throws ZipcodeFinderException;
}

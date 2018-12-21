package com.edn.poc.rabbitmq.server.finder;

import com.edn.poc.rabbitmq.server.dto.response.IAddress;
import com.edn.poc.rabbitmq.server.exception.ZipcodeFinderException;

public interface ZipcodeFinder {

    IAddress find(String zipcode) throws ZipcodeFinderException;
}

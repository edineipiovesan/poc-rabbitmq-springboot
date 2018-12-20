package com.edn.poc.rabbitmq.server.model.impl;

import com.edn.poc.rabbitmq.server.model.BaseCity;
import com.edn.poc.rabbitmq.server.model.BaseState;
import com.edn.poc.rabbitmq.server.model.BasicAddress;

public class ViaCEPAddress implements BasicAddress {
    @Override
    public String getCep() {
        return null;
    }

    @Override
    public String getLogradouro() {
        return null;
    }

    @Override
    public String getBairro() {
        return null;
    }

    @Override
    public BaseCity getCidade() {
        return null;
    }

    @Override
    public BaseState getEstado() {
        return null;
    }
}

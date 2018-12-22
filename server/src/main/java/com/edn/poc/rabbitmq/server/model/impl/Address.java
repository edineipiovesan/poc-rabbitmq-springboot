package com.edn.poc.rabbitmq.server.model.impl;

import com.edn.poc.rabbitmq.server.model.BaseAddress;
import com.edn.poc.rabbitmq.server.model.BaseCity;
import com.edn.poc.rabbitmq.server.model.BaseState;

public class Address implements BaseAddress {
    private String cep;
    private String logradouro;
    private String bairro;
    private BaseCity cidade;
    private BaseState estado;

    @Override
    public String getCep() {
        return cep;
    }

    @Override
    public String getLogradouro() {
        return logradouro;
    }

    @Override
    public String getBairro() {
        return bairro;
    }

    @Override
    public BaseCity getCidade() {
        return cidade;
    }

    @Override
    public BaseState getEstado() {
        return estado;
    }
}

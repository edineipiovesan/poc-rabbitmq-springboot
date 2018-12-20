package com.edn.poc.rabbitmq.server.model;

public interface BasicAddress {

    String getCep();
    String getLogradouro();
    String getBairro();
    BaseCity getCidade();
    BaseState getEstado();
}

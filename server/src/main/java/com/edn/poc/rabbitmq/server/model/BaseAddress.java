package com.edn.poc.rabbitmq.server.model;

public interface BaseAddress {

    String getCep();

    String getLogradouro();

    String getBairro();

    BaseCity getCidade();

    BaseState getEstado();
}

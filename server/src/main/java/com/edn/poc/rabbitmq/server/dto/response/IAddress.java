package com.edn.poc.rabbitmq.server.dto.response;

public interface IAddress {
    String getCep();

    String getLogradouro();

    String getBairro();

    String getCidade();

    String getEstado();
}

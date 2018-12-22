package com.edn.poc.rabbitmq.server.model.impl;

import com.edn.poc.rabbitmq.server.model.BaseCity;

public class City implements BaseCity {
    private String city;

    public City(String city) {
        this.city = city;
    }

    @Override
    public String getNome() {
        return city;
    }
}

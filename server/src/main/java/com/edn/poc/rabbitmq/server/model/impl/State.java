package com.edn.poc.rabbitmq.server.model.impl;

import com.edn.poc.rabbitmq.server.model.BaseState;

public class State implements BaseState {

    private String state;
    private String initials;

    public State(String state, String initials) {
        this.state = state;
        this.initials = initials;
    }

    @Override
    public String getNome() {
        return state;
    }

    @Override
    public String getSigla() {
        return initials;
    }
}

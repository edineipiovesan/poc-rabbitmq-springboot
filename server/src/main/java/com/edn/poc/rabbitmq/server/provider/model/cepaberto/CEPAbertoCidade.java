package com.edn.poc.rabbitmq.server.provider.model.cepaberto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CEPAbertoCidade {

    @JsonProperty(value = "nome", required = true)
    private String nome;

    public CEPAbertoCidade() {
    }

    @JsonCreator
    public CEPAbertoCidade(@JsonProperty(value = "nome", required = true) String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "CEPAbertoCidade{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
package com.edn.poc.rabbitmq.server.provider.model.postmon;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PostmonEstado {

    @JsonProperty(value = "nome", required = true)
    private String nome;

    public PostmonEstado() {
    }

    @JsonCreator
    public PostmonEstado(@JsonProperty(value = "nome", required = true) String nome) {
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
        return "PostmonEstado{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
package com.edn.poc.rabbitmq.server.model.postmon;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PostmonEstado {

    @JsonProperty("nome")
    private String nome;

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
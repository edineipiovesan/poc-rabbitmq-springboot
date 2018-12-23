package com.edn.poc.rabbitmq.server.model.cepaberto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CEPAbertoCidade {

    @JsonProperty("nome")
    private String nome;

    public CEPAbertoCidade() {
    }

    public CEPAbertoCidade(String nome) {
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
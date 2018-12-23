package com.edn.poc.rabbitmq.server.model.cepaberto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CEPAbertoCidade {

    @JsonProperty("ddd")
    private int ddd;

    @JsonProperty("ibge")
    private String ibge;

    @JsonProperty("nome")
    private String nome;

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return
                "cidade{" +
                        "ddd = '" + ddd + '\'' +
                        ",ibge = '" + ibge + '\'' +
                        ",nome = '" + nome + '\'' +
                        "}";
    }
}
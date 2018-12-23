package com.edn.poc.rabbitmq.server.model.cepaberto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CEPAbertoEstado {

    @JsonProperty("sigla")
    private String sigla;

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return
                "estado{" +
                        "sigla = '" + sigla + '\'' +
                        "}";
    }
}
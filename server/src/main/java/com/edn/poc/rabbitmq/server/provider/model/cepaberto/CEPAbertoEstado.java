package com.edn.poc.rabbitmq.server.provider.model.cepaberto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CEPAbertoEstado {

    @JsonProperty(value = "sigla", required = true)
    private String sigla;

    public CEPAbertoEstado() {
    }

    @JsonCreator
    public CEPAbertoEstado(@JsonProperty(value = "sigla", required = true) String sigla) {
        this.sigla = sigla;
    }

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
package com.edn.poc.rabbitmq.server.model.postmon;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PostmonEstado {

    @JsonProperty("codigo_ibge")
    private String codigoIbge;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("area_km2")
    private String areaKm2;

    public String getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAreaKm2() {
        return areaKm2;
    }

    public void setAreaKm2(String areaKm2) {
        this.areaKm2 = areaKm2;
    }

    @Override
    public String toString() {
        return
                "PostmonEstado{" +
                        "codigo_ibge = '" + codigoIbge + '\'' +
                        ",nome = '" + nome + '\'' +
                        ",area_km2 = '" + areaKm2 + '\'' +
                        "}";
    }
}
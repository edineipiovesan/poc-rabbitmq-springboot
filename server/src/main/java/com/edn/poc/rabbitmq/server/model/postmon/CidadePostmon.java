package com.edn.poc.rabbitmq.server.model.postmon;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CidadePostmon {

    @JsonProperty("codigo_ibge")
    private String codigoIbge;

    @JsonProperty("area_km2")
    private String areaKm2;

    public String getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
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
                "CidadePostmon{" +
                        "codigo_ibge = '" + codigoIbge + '\'' +
                        ",area_km2 = '" + areaKm2 + '\'' +
                        "}";
    }
}
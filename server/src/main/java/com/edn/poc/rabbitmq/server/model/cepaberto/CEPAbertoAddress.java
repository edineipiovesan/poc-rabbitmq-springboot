package com.edn.poc.rabbitmq.server.model.cepaberto;

import com.edn.poc.rabbitmq.server.model.IAddress;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CEPAbertoAddress implements IAddress {

    @JsonProperty("altitude")
    private double altitude;

    @JsonProperty("cidade")
    private CEPAbertoCidade CEPAbertoCidade;

    @JsonProperty("estado")
    private CEPAbertoEstado CEPAbertoEstado;

    @JsonProperty("logradouro")
    private String logradouro;

    @JsonProperty("bairro")
    private String bairro;

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("longitude")
    private String longitude;

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public CEPAbertoCidade getCEPAbertoCidade() {
        return CEPAbertoCidade;
    }

    public void setCEPAbertoCidade(CEPAbertoCidade CEPAbertoCidade) {
        this.CEPAbertoCidade = CEPAbertoCidade;
    }

    public CEPAbertoEstado getCEPAbertoEstado() {
        return CEPAbertoEstado;
    }

    public void setCEPAbertoEstado(CEPAbertoEstado CEPAbertoEstado) {
        this.CEPAbertoEstado = CEPAbertoEstado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public String getCidade() {
        return getCEPAbertoCidade().getNome();
    }

    @Override
    public String getEstado() {
        return getCEPAbertoEstado().getSigla();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return
                "CEPAbertoAddress{" +
                        "altitude = '" + altitude + '\'' +
                        ",cidade = '" + CEPAbertoCidade + '\'' +
                        ",estado = '" + CEPAbertoEstado + '\'' +
                        ",logradouro = '" + logradouro + '\'' +
                        ",bairro = '" + bairro + '\'' +
                        ",latitude = '" + latitude + '\'' +
                        ",cep = '" + cep + '\'' +
                        ",longitude = '" + longitude + '\'' +
                        "}";
    }
}
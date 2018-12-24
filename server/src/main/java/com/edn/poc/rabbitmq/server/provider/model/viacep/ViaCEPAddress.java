package com.edn.poc.rabbitmq.server.provider.model.viacep;

import com.edn.poc.rabbitmq.server.provider.model.IAddress;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ViaCEPAddress implements IAddress {

    @JsonProperty(value = "uf", required = true)
    private String uf;

    @JsonProperty(value = "logradouro", required = true)
    private String logradouro;

    @JsonProperty(value = "bairro", required = true)
    private String bairro;

    @JsonProperty(value = "localidade", required = true)
    private String localidade;

    @JsonProperty(value = "cep", required = true)
    private String cep;

    @JsonCreator
    public ViaCEPAddress(@JsonProperty(value = "uf", required = true) String uf,
                         @JsonProperty(value = "logradouro", required = true) String logradouro,
                         @JsonProperty(value = "bairro", required = true) String bairro,
                         @JsonProperty(value = "localidade", required = true) String localidade,
                         @JsonProperty(value = "cep", required = true) String cep) {
        this.uf = uf;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.cep = cep;
    }

    public ViaCEPAddress() {
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
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
        return getLocalidade();
    }

    @Override
    public String getEstado() {
        return getUf();
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "ViaCEPAddress{" +
                "uf='" + uf + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
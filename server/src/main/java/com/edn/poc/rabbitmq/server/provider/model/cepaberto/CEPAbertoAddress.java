package com.edn.poc.rabbitmq.server.provider.model.cepaberto;

import com.edn.poc.rabbitmq.server.provider.model.IAddress;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CEPAbertoAddress implements IAddress {

    @JsonProperty(value = "cidade", required = true)
    private CEPAbertoCidade CEPAbertoCidade;

    @JsonProperty(value = "estado", required = true)
    private CEPAbertoEstado CEPAbertoEstado;

    @JsonProperty(value = "logradouro", required = true)
    private String logradouro;

    @JsonProperty(value = "bairro", required = true)
    private String bairro;

    @JsonProperty(value = "cep", required = true)
    private String cep;

    @JsonCreator
    public CEPAbertoAddress(@JsonProperty(value = "cidade", required = true) CEPAbertoCidade CEPAbertoCidade,
                            @JsonProperty(value = "estado", required = true) CEPAbertoEstado CEPAbertoEstado,
                            @JsonProperty(value = "logradouro", required = true) String logradouro,
                            @JsonProperty(value = "bairro", required = true) String bairro,
                            @JsonProperty(value = "cep", required = true) String cep) {
        this.CEPAbertoCidade = CEPAbertoCidade;
        this.CEPAbertoEstado = CEPAbertoEstado;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cep = cep;
    }

    public CEPAbertoAddress() {
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "CEPAbertoAddress{" +
                "CEPAbertoCidade=" + CEPAbertoCidade +
                ", CEPAbertoEstado=" + CEPAbertoEstado +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
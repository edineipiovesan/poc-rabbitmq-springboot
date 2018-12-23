package com.edn.poc.rabbitmq.server.model.cepaberto;

import com.edn.poc.rabbitmq.server.model.IAddress;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CEPAbertoAddress implements IAddress {

    @JsonProperty("cidade")
    private CEPAbertoCidade CEPAbertoCidade;

    @JsonProperty("estado")
    private CEPAbertoEstado CEPAbertoEstado;

    @JsonProperty("logradouro")
    private String logradouro;

    @JsonProperty("bairro")
    private String bairro;

    @JsonProperty("cep")
    private String cep;

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
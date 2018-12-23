package com.edn.poc.rabbitmq.server.model.viacep;

import com.edn.poc.rabbitmq.server.model.IAddress;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ViaCEPAddress implements IAddress {

    @JsonProperty("uf")
    private String uf;

    @JsonProperty("complemento")
    private String complemento;

    @JsonProperty("logradouro")
    private String logradouro;

    @JsonProperty("bairro")
    private String bairro;

    @JsonProperty("localidade")
    private String localidade;

    @JsonProperty("ibge")
    private String ibge;

    @JsonProperty("unidade")
    private String unidade;

    @JsonProperty("gia")
    private String gia;

    @JsonProperty("cep")
    private String cep;

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return
                "ViaCEPAddress{" +
                        "uf = '" + uf + '\'' +
                        ",complemento = '" + complemento + '\'' +
                        ",logradouro = '" + logradouro + '\'' +
                        ",bairro = '" + bairro + '\'' +
                        ",localidade = '" + localidade + '\'' +
                        ",ibge = '" + ibge + '\'' +
                        ",unidade = '" + unidade + '\'' +
                        ",gia = '" + gia + '\'' +
                        ",cep = '" + cep + '\'' +
                        "}";
    }
}
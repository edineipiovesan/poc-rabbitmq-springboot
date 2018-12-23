package com.edn.poc.rabbitmq.server.model.postmon;

import com.edn.poc.rabbitmq.server.model.IAddress;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PostmonAddress implements IAddress {

    @JsonProperty("cidade")
    private String cidade;

    @JsonProperty("estado")
    private String estadoSigla;

    @JsonProperty("bairro")
    private String bairro;

    @JsonProperty("logradouro")
    private String logradouro;

    @JsonProperty("estado_info")
    private PostmonEstado postmonEstado;

    @JsonProperty("cidade_info")
    private CidadePostmon cidadePostmon;

    @JsonProperty("cep")
    private String cep;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String getEstado() {
        return getPostmonEstado().getNome();
    }

    @JsonGetter
    public String getEstadoSigla() {
        return estadoSigla;
    }

    public void setEstadoSigla(String estadoSigla) {
        this.estadoSigla = estadoSigla;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public PostmonEstado getPostmonEstado() {
        return postmonEstado;
    }

    public void setPostmonEstado(PostmonEstado postmonEstado) {
        this.postmonEstado = postmonEstado;
    }

    public CidadePostmon getCidadePostmon() {
        return cidadePostmon;
    }

    public void setCidadePostmon(CidadePostmon cidadePostmon) {
        this.cidadePostmon = cidadePostmon;
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
                "PostmonAddress{" +
                        "cidade = '" + cidade + '\'' +
                        ",estadoSigla = '" + estadoSigla + '\'' +
                        ",bairro = '" + bairro + '\'' +
                        ",logradouro = '" + logradouro + '\'' +
                        ",estado_info = '" + postmonEstado + '\'' +
                        ",cidade_info = '" + cidadePostmon + '\'' +
                        ",cep = '" + cep + '\'' +
                        "}";
    }
}
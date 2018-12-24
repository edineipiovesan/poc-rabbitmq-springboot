package com.edn.poc.rabbitmq.server.provider.model.postmon;

import com.edn.poc.rabbitmq.server.provider.model.IAddress;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PostmonAddress implements IAddress {

    @JsonProperty(value = "cidade", required = true)
    private String cidade;

    @JsonProperty(value = "bairro", required = true)
    private String bairro;

    @JsonProperty(value = "logradouro", required = true)
    private String logradouro;

    @JsonProperty(value = "estado_info", required = true)
    private PostmonEstado postmonEstado;

    @JsonProperty(value = "cep", required = true)
    private String cep;

    @JsonCreator
    public PostmonAddress(@JsonProperty(value = "cidade", required = true) String cidade,
                          @JsonProperty(value = "bairro", required = true) String bairro,
                          @JsonProperty(value = "logradouro", required = true) String logradouro,
                          @JsonProperty(value = "estado_info", required = true) PostmonEstado postmonEstado,
                          @JsonProperty(value = "cep", required = true) String cep) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.postmonEstado = postmonEstado;
        this.cep = cep;
    }

    public PostmonAddress() {
    }

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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "PostmonAddress{" +
                "cidade='" + cidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", postmonEstado=" + postmonEstado +
                ", cep='" + cep + '\'' +
                '}';
    }
}
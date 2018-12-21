package com.edn.poc.rabbitmq.server.dto.response;

import com.edn.poc.rabbitmq.server.model.BaseAddress;

public class AddressResponse {
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;

    public AddressResponse(String cep, String logradouro, String bairro, String cidade, String estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public AddressResponse(BaseAddress address) {
        this.cep = address.getCep();
        this.logradouro = address.getLogradouro();
        this.bairro = address.getBairro();
        this.cidade = address.getCidade().getNome();
        this.estado = address.getEstado().getNome();
    }

    public AddressResponse() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

package com.edn.poc.rabbitmq.server.model.impl;

import com.edn.poc.rabbitmq.server.model.BaseCity;
import com.edn.poc.rabbitmq.server.model.BaseState;
import com.edn.poc.rabbitmq.server.model.BasicAddress;

public class CEPAbertoAddress implements BasicAddress {

    private Double altitude;
    private String cep;
    private String latitude;
    private String longitude;
    private String logradouro;
    private String bairro;
    private CidadeBean cidade;
    private EstadoBean estado;

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public CidadeBean getCidade() {
        return cidade;
    }

    public void setCidade(CidadeBean cidade) {
        this.cidade = cidade;
    }

    public EstadoBean getEstado() {
        return estado;
    }

    public void setEstado(EstadoBean estado) {
        this.estado = estado;
    }

    public static class CidadeBean implements BaseCity {

        private int ddd;
        private String ibge;
        private String nome;

        public int getDdd() {
            return ddd;
        }

        public void setDdd(int ddd) {
            this.ddd = ddd;
        }

        public String getIbge() {
            return ibge;
        }

        public void setIbge(String ibge) {
            this.ibge = ibge;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        @Override
        public String toString() {
            return "CidadeBean{" +
                    "ddd=" + ddd +
                    ", ibge='" + ibge + '\'' +
                    ", nome='" + nome + '\'' +
                    '}';
        }

        @Override
        public String getCidade() {
            return nome;
        }
    }

    public static class EstadoBean implements BaseState {

        private String sigla;


        public String getSigla() {
            return sigla;
        }

        public void setSigla(String sigla) {
            this.sigla = sigla;
        }

        @Override
        public String toString() {
            return "EstadoBean{" +
                    "sigla='" + sigla + '\'' +
                    '}';
        }

        @Override
        public String getEstado() {
            // TODO: nome do estado
            return sigla;
        }
    }

    @Override
    public String toString() {
        return "CEPAbertoAddress{" +
                "altitude=" + altitude +
                ", cep='" + cep + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade=" + cidade +
                ", estado=" + estado +
                '}';
    }
}

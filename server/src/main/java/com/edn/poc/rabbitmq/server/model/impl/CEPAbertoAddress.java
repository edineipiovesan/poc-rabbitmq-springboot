package com.edn.poc.rabbitmq.server.model.impl;

import com.edn.poc.rabbitmq.server.model.BaseCity;
import com.edn.poc.rabbitmq.server.model.BaseState;
import com.edn.poc.rabbitmq.server.model.BaseAddress;

public class CEPAbertoAddress implements BaseAddress {

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

    @Override
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

    @Override
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @Override
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public CidadeBean getCidade() {
        return cidade;
    }

    public void setCidade(CidadeBean cidade) {
        this.cidade = cidade;
    }

    @Override
    public EstadoBean getEstado() {
        return estado;
    }

    public void setEstado(EstadoBean estado) {
        this.estado = estado;
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

    public class CidadeBean implements BaseCity {

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

        @Override
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

    }

    public class EstadoBean implements BaseState {

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
        public String getNome() {
            // TODO: nome do estado
            return sigla;
        }
    }
}

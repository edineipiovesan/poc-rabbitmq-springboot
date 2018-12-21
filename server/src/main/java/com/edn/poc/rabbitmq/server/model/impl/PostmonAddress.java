package com.edn.poc.rabbitmq.server.model.impl;

import com.edn.poc.rabbitmq.server.model.BaseAddress;
import com.edn.poc.rabbitmq.server.model.BaseCity;
import com.edn.poc.rabbitmq.server.model.BaseState;

public class PostmonAddress implements BaseAddress {

    private String bairro;
    private String cidade;
    private String logradouro;
    private EstadoInfoBean estado_info;
    private String cep;
    private CidadeInfoBean cidade_info;
    private String estado;

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public BaseCity getCidade() {
        return new City(cidade);
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public EstadoInfoBean getEstado_info() {
        return estado_info;
    }

    public void setEstado_info(EstadoInfoBean estado_info) {
        this.estado_info = estado_info;
    }

    @Override
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public CidadeInfoBean getCidade_info() {
        return cidade_info;
    }

    public void setCidade_info(CidadeInfoBean cidade_info) {
        this.cidade_info = cidade_info;
    }

    @Override
    public BaseState getEstado() {
        return new State(getEstado_info().getNome(), estado);
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public static class EstadoInfoBean implements BaseState {
        /**
         * area_km2 : 340.110,385
         * codigo_ibge : 52
         * nome : Goiás
         */

        private String area_km2;
        private String codigo_ibge;
        private String nome;

        public String getArea_km2() {
            return area_km2;
        }

        public void setArea_km2(String area_km2) {
            this.area_km2 = area_km2;
        }

        public String getCodigo_ibge() {
            return codigo_ibge;
        }

        public void setCodigo_ibge(String codigo_ibge) {
            this.codigo_ibge = codigo_ibge;
        }

        @Override
        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        @Override
        public String getSigla() {
            return nome;
        }
    }

    public class CidadeInfoBean implements BaseCity {
        /**
         * area_km2 : 728,841
         * codigo_ibge : 5208707
         */

        private String area_km2;
        private String codigo_ibge;

        public String getArea_km2() {
            return area_km2;
        }

        public void setArea_km2(String area_km2) {
            this.area_km2 = area_km2;
        }

        public String getCodigo_ibge() {
            return codigo_ibge;
        }

        public void setCodigo_ibge(String codigo_ibge) {
            this.codigo_ibge = codigo_ibge;
        }

        @Override
        public String getNome() {
            return cidade;
        }
    }
}

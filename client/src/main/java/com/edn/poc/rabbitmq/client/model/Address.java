package com.edn.poc.rabbitmq.client.model;

public class Address {

    /**
     * altitude : 779.4
     * cep : 74393250
     * latitude : -16.6868912
     * longitude : -49.2647943
     * logradouro : Rua FP 27
     * bairro : Recreio do Funcionário Público
     * cidade : {"ddd":62,"ibge":"5208707","nome":"Goiânia"}
     * estado : {"sigla":"GO"}
     */

    private double altitude;
    private String cep;
    private String latitude;
    private String longitude;
    private String logradouro;
    private String bairro;
    private CidadeBean cidade;
    private EstadoBean estado;

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
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

    public static class CidadeBean {
        /**
         * ddd : 62
         * ibge : 5208707
         * nome : Goiânia
         */

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
    }

    public static class EstadoBean {
        /**
         * sigla : GO
         */

        private String sigla;

        public String getSigla() {
            return sigla;
        }

        public void setSigla(String sigla) {
            this.sigla = sigla;
        }
    }
}

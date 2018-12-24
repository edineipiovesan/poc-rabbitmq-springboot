package com.edn.poc.rabbitmq.server.provider.model.viacep;

public final class ViaCEPAddressBuilder {
    private ViaCEPAddress viaCEPAddress;

    private ViaCEPAddressBuilder() {
        viaCEPAddress = new ViaCEPAddress();
    }

    public static ViaCEPAddressBuilder aViaCEPAddress() {
        return new ViaCEPAddressBuilder();
    }

    public ViaCEPAddressBuilder withEstado(String estado) {
        viaCEPAddress.setUf(estado);
        return this;
    }

    public ViaCEPAddressBuilder withLogradouro(String logradouro) {
        viaCEPAddress.setLogradouro(logradouro);
        return this;
    }

    public ViaCEPAddressBuilder withBairro(String bairro) {
        viaCEPAddress.setBairro(bairro);
        return this;
    }

    public ViaCEPAddressBuilder withCidade(String cidade) {
        viaCEPAddress.setLocalidade(cidade);
        return this;
    }

    public ViaCEPAddressBuilder withCep(String cep) {
        viaCEPAddress.setCep(cep);
        return this;
    }

    public ViaCEPAddressBuilder but() {
        return aViaCEPAddress()
                .withEstado(viaCEPAddress.getUf())
                .withLogradouro(viaCEPAddress.getLogradouro())
                .withBairro(viaCEPAddress.getBairro())
                .withCidade(viaCEPAddress.getLocalidade())
                .withCep(viaCEPAddress.getCep());
    }

    public ViaCEPAddress build() {
        return viaCEPAddress;
    }
}

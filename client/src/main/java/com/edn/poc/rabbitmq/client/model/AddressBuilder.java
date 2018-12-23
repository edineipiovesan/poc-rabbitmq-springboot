package com.edn.poc.rabbitmq.client.model;

public final class AddressBuilder {
    private Address address;

    private AddressBuilder() {
        address = new Address();
    }

    public static AddressBuilder anAddress() {
        return new AddressBuilder();
    }

    public AddressBuilder withCep(String cep) {
        address.setCep(cep);
        return this;
    }

    public AddressBuilder withLogradouro(String logradouro) {
        address.setLogradouro(logradouro);
        return this;
    }

    public AddressBuilder withBairro(String bairro) {
        address.setBairro(bairro);
        return this;
    }

    public AddressBuilder withCidade(String cidade) {
        address.setCidade(cidade);
        return this;
    }

    public AddressBuilder withEstado(String estado) {
        address.setEstado(estado);
        return this;
    }

    public AddressBuilder but() {
        return anAddress().withCep(address.getCep()).withLogradouro(address.getLogradouro()).withBairro(address.getBairro()).withCidade(address.getCidade()).withEstado(address.getEstado());
    }

    public Address build() {
        return address;
    }
}

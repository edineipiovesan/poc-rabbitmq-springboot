package com.edn.poc.rabbitmq.server.provider.model.cepaberto;

public final class CEPAbertoAddressBuilder {
    private CEPAbertoAddress cEPAbertoAddress;

    private CEPAbertoAddressBuilder() {
        cEPAbertoAddress = new CEPAbertoAddress();
    }

    public static CEPAbertoAddressBuilder aCEPAbertoAddress() {
        return new CEPAbertoAddressBuilder();
    }

    public CEPAbertoAddressBuilder withCidade(String cidade) {
        cEPAbertoAddress.setCEPAbertoCidade(new CEPAbertoCidade(cidade));
        return this;
    }

    public CEPAbertoAddressBuilder withEstado(String estado) {
        cEPAbertoAddress.setCEPAbertoEstado(new CEPAbertoEstado(estado));
        return this;
    }

    public CEPAbertoAddressBuilder withLogradouro(String logradouro) {
        cEPAbertoAddress.setLogradouro(logradouro);
        return this;
    }

    public CEPAbertoAddressBuilder withBairro(String bairro) {
        cEPAbertoAddress.setBairro(bairro);
        return this;
    }

    public CEPAbertoAddressBuilder withCep(String cep) {
        cEPAbertoAddress.setCep(cep);
        return this;
    }

    public CEPAbertoAddressBuilder but() {
        return aCEPAbertoAddress()
                .withCidade(cEPAbertoAddress.getCEPAbertoCidade().getNome())
                .withEstado(cEPAbertoAddress.getCEPAbertoEstado().getSigla())
                .withLogradouro(cEPAbertoAddress.getLogradouro())
                .withBairro(cEPAbertoAddress.getBairro())
                .withCep(cEPAbertoAddress.getCep());
    }

    public CEPAbertoAddress build() {
        return cEPAbertoAddress;
    }
}

package com.edn.poc.rabbitmq.server.configuration.api.impl;


import com.edn.poc.rabbitmq.server.configuration.api.ApiInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration("CEPAbertoApiInfo")
@PropertySource("classpath:cepaberto.properties")
public class CEPAbertoApiInfo implements ApiInfo {

    @Value("${cepaberto.api.url}")
    private String url;

    @Value("${cepaberto.api.format}")
    private String format;

    @Value("${cepaberto.api.token}")
    private String token;

    @Value("${cepaberto.api.endpoint}")
    private String endpoint;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}

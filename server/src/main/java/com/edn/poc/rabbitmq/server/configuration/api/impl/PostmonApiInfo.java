package com.edn.poc.rabbitmq.server.configuration.api.impl;


import com.edn.poc.rabbitmq.server.configuration.api.ApiInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration("PostmonApiInfo")
@PropertySource("classpath:postmon.properties")
public class PostmonApiInfo implements ApiInfo {

    @Value("${postmon.api.url}")
    private String url;

    @Value("${postmon.api.format}")
    private String format;

    @Value("${postmon.api.token}")
    private String token;

    @Value("${postmon.api.endpoint}")
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

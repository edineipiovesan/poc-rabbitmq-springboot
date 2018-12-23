package com.edn.poc.rabbitmq.client.service;

import com.edn.poc.rabbitmq.client.exception.ZipCodeGeneratorException;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class ZipCodeGeneratorServiceTest {

    private ZipCodeGeneratorService zipCodeGeneratorService;

    @Before
    public void init() {
        zipCodeGeneratorService = new ZipCodeGeneratorService();
    }

    @Test
    public void getZipcodeTest() throws ZipCodeGeneratorException {
        String zipcode = zipCodeGeneratorService.getZipcode();
        log.info("Zipcode generated is {}", zipcode);
        assertThat(zipcode).isNotNull();
    }
}

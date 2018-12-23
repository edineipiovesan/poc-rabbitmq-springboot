package com.edn.poc.rabbitmq.server.util;

import com.edn.poc.rabbitmq.server.exception.ZipcodeInvalidException;
import org.junit.Test;

import static com.edn.poc.rabbitmq.server.util.StandardUtils.standardizeZipcode;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.util.StringUtils.containsWhitespace;
import static org.springframework.util.StringUtils.hasLength;

public class StandardUtilsTest {

    @Test
    public void zipcodeWithHyphenTest() throws ZipcodeInvalidException {
        String zipcode = standardizeZipcode("74393-250");

        assertThat(zipcode).isNotNull();
        assertThat(zipcode.length()).isEqualTo(8);
        assertThat(hasLength(zipcode)).isTrue();
        assertThat(containsWhitespace(zipcode)).isFalse();
        assertThat(zipcode).isEqualTo("74393250");
    }

    @Test
    public void zipcodeOnlyNumbersTest() throws ZipcodeInvalidException {
        String zipcode = standardizeZipcode("74393250");

        assertThat(zipcode).isNotNull();
        assertThat(zipcode.length()).isEqualTo(8);
        assertThat(hasLength(zipcode)).isTrue();
        assertThat(containsWhitespace(zipcode)).isFalse();
        assertThat(zipcode).isEqualTo("74393250");
    }

    @Test(expected = ZipcodeInvalidException.class)
    public void zipcodeWithLettersTest() throws ZipcodeInvalidException {
        standardizeZipcode("ZIP393-785");
    }

    @Test(expected = ZipcodeInvalidException.class)
    public void nullZipcodeTest() throws ZipcodeInvalidException {
        standardizeZipcode(null);
    }

    @Test(expected = ZipcodeInvalidException.class)
    public void zipcodeWithLessNumbers() throws ZipcodeInvalidException {
        standardizeZipcode("7439325");
    }

    @Test(expected = ZipcodeInvalidException.class)
    public void zipcodeWithMoreNumbers() throws ZipcodeInvalidException {
        standardizeZipcode("74393939250");
    }

    @Test(expected = ZipcodeInvalidException.class)
    public void zipcodeWithSpecialChars() throws ZipcodeInvalidException {
        standardizeZipcode("743*3-2/5-");
    }

    @Test(expected = ZipcodeInvalidException.class)
    public void zipcodeWithWhiteSpaces() throws ZipcodeInvalidException {
        standardizeZipcode("74 393250");
    }

    @Test(expected = ZipcodeInvalidException.class)
    public void zipcodeWithEmptyString() throws ZipcodeInvalidException {
        standardizeZipcode("");
    }
}

package com.edn.poc.rabbitmq.server.util;

import com.edn.poc.rabbitmq.server.exception.ZipcodeInvalidException;
import org.springframework.util.StringUtils;

public class StandardUtils {

    private StandardUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Utils classes should not be instantiated.");
    }

    public static String standardizeZipcode(String zipcode) throws ZipcodeInvalidException {
        if (StringUtils.isEmpty(zipcode))
            throw new ZipcodeInvalidException(String.format("Zipcode %s is not valid. Please provida a zipcode in " +
                    "standard 12345-678 or 12345678", zipcode));

        String hyphenPattern = "^\\d{5}[-]\\d{3}$";
        String onlyNumberPattern = "^\\d{8}$";

        if (zipcode.matches(onlyNumberPattern))
            return zipcode;

        if (zipcode.matches(hyphenPattern))
            return StringUtils.delete(zipcode, "-");

        throw new ZipcodeInvalidException(String.format("Zipcode %s is not valid. Please provida a zipcode in " +
                "standard 12345-678 or 12345678", zipcode));
    }

}

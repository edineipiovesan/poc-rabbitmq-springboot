package com.edn.poc.rabbitmq.client.service;

import com.edn.poc.rabbitmq.client.exception.ZipCodeGeneratorException;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class ZipCodeGeneratorService {


    public String getZipcode() throws ZipCodeGeneratorException {
        InputStream inputStream = Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResourceAsStream("cep.csv"), "Resource not found.");

        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
            List<String[]> csvData = reader.readAll();
            return csvData.get(new Random().nextInt(csvData.size() - 1))[0];
        } catch (IOException e) {
            throw new ZipCodeGeneratorException("Zip Code CSV file not found.");
        }
    }
}

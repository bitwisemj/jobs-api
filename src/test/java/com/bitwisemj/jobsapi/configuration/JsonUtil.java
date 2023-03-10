package com.bitwisemj.jobsapi.configuration;

import java.io.FileInputStream;
import java.io.InputStream;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

public class JsonUtil {
    
    @SneakyThrows
    public static String getJsonFromFile(final String filename) {

        try (final InputStream inputStream = new FileInputStream(filename)) {
            
            return new String(inputStream.readAllBytes());
        }
    }

    @SneakyThrows
    public static <T> T jsonToObject(final String json, Class<T> clazz) {

        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return mapper.readValue(json, clazz);
    }
}

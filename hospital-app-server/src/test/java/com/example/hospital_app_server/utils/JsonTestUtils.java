package com.example.hospital_app_server.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class JsonTestUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonTestUtils() {
    }

    public static String convertToJsonString(Object obj) {
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert object to JSON string", e);
        }
    }
}

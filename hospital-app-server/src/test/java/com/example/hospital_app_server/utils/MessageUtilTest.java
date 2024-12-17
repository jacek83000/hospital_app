package com.example.hospital_app_server.utils;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.example.hospital_app_server.utils.MessageUtil.getMessage;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MessageUtilTest {

    @Test
    void when_messageRetrievedFromProperties_then_stringIsReturned() {
        Locale locale = Locale.of("en");
        String property = "messages.validation.notnull";
        String expectedRes = "Field can't be null.";

        String message = getMessage(property, locale);

        assertThat(message).isEqualTo(expectedRes);
    }
}

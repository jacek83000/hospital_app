package com.example.hospital_app_server.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyServiceITest {
    private static final String BASE_URL = "https://fake-json-api.mock.beeceptor.com";
    private final WebTestClient webTestClient;

    @Autowired
    public CompanyServiceITest(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    @Test
    void when_companyIsRetrievedById_then_dataIsReturnedFromExternalApi() {
        webTestClient.get()
                .uri(BASE_URL + "/companies/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("name").exists()
                .jsonPath("address").exists();
    }
}

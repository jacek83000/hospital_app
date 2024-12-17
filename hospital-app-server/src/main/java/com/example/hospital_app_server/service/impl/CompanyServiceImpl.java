package com.example.hospital_app_server.service.impl;

import com.example.hospital_app_server.dto.response.CompanyGetResDTO;
import com.example.hospital_app_server.exception.ResourceNotFoundException;
import com.example.hospital_app_server.service.CompanyService;
import io.netty.handler.timeout.ReadTimeoutException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.naming.AuthenticationException;
import java.time.Duration;

import static com.example.hospital_app_server.utils.MessageUtil.getMessage;

@Service
public class CompanyServiceImpl implements CompanyService {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final WebClient webClient;

    @Autowired
    public CompanyServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public CompanyGetResDTO findById(int id) {
        try {
            return webClient.get()
                    .uri("/users/" + id)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError,
                            response -> switch (response.statusCode().value()) {
                                case 400 -> Mono.error(new BadRequestException("Bad request error"));
                                case 401, 403 -> Mono.error(new AuthenticationException("Authentication error"));
                                case 404 -> Mono.error(new ResourceNotFoundException("Could not find the resource"));
                                case 500 -> Mono.error(new RuntimeException("Server error"));
                                default -> Mono.error(new Exception("Unexpected error"));
                            })
                    .bodyToMono(CompanyGetResDTO.class)
                    .block(Duration.ofSeconds(5));
        } catch (ReadTimeoutException | ResourceNotFoundException exception) {
            return new CompanyGetResDTO(getMessage("messages.error.failed-to-load"));
        }
    }
}

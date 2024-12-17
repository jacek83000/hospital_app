package com.example.hospital_app_server.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
public class PersonControllerITest {
    private static final String BASE_PATH = "/people";
    private static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());

    private final int id = 10001;
    private final MockMvc mockMvc;

    @Autowired
    public PersonControllerITest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void when_allPeopleAreRetrieved_then_allPeopleAreReturned() throws Exception {
        mockMvc.perform(get(BASE_PATH)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void when_personIsRetrievedById_then_personIsReturned() throws Exception {
        mockMvc.perform(get(BASE_PATH + "/{id}", id)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("firstName1")))
                .andExpect(jsonPath("$.lastName", is("lastName1")))
                .andExpect(jsonPath("$.email", is("doctor1@hospital.com")))
                .andExpect(jsonPath("$.contactNumber", is("+48 111 111 111")));
    }
}

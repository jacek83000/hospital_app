package com.example.hospital_app_server.controller;

import com.example.hospital_app_server.dto.request.VisitCreateReqDTO;
import com.example.hospital_app_server.dto.request.VisitUpdateReqDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.hospital_app_server.utils.JsonTestUtils.convertToJsonString;
import static com.example.hospital_app_server.utils.TestEntityDTOFactory.getValidVisitCreateReqDTO;
import static com.example.hospital_app_server.utils.TestEntityDTOFactory.getValidVisitUpdateReqDTO;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
public class VisitControllerITest {
    private static final String BASE_PATH = "/visits";
    private static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());

    private final int id = 10001;
    private final MockMvc mockMvc;

    @Autowired
    public VisitControllerITest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void when_allVisitsAreRetrieved_then_allVisitsAreReturned() throws Exception {
        mockMvc.perform(get(BASE_PATH)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void when_visitIsRetrievedById_then_visitIsReturned() throws Exception {
        mockMvc.perform(get(BASE_PATH + "/{id}", id)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", is(notNullValue())))
                .andExpect(jsonPath("$.assurance", is(false)))
                .andExpect(jsonPath("$.price", is(150.0)))
                .andExpect(jsonPath("$.doctor", is(notNullValue())))
                .andExpect(jsonPath("$.patient", is(notNullValue())));
    }

    @Test
    @Transactional
    void when_visitIsCreated_then_visitIsPersistedInDatabase() throws Exception {
        VisitCreateReqDTO reqDTO = getValidVisitCreateReqDTO();

        mockMvc.perform(post(BASE_PATH)
                        .content(convertToJsonString(reqDTO))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.date", is(notNullValue())))
                .andExpect(jsonPath("$.assurance", is(true)))
                .andExpect(jsonPath("$.price", is(129.0)))
                .andExpect(jsonPath("$.doctor", is(notNullValue())))
                .andExpect(jsonPath("$.patient", is(notNullValue())));
    }

    @Test
    @Transactional
    void when_visitIsUpdated_then_visitIsModifiedInDatabase() throws Exception {
        VisitUpdateReqDTO reqDTO = getValidVisitUpdateReqDTO(id);

        mockMvc.perform(put(BASE_PATH, id)
                        .content(convertToJsonString(reqDTO))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(reqDTO.getId())))
                .andExpect(jsonPath("$.date", is(notNullValue())))
                .andExpect(jsonPath("$.assurance", is(false)))
                .andExpect(jsonPath("$.price", is(171.9)))
                .andExpect(jsonPath("$.doctor", is(notNullValue())))
                .andExpect(jsonPath("$.patient", is(notNullValue())));
    }

    @Test
    @Transactional
    void when_visitIsDeletedById_then_visitIsRemovedFromDatabase() throws Exception {
        mockMvc.perform(delete(BASE_PATH + "/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

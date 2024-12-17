package com.example.hospital_app_server.controller;

import com.example.hospital_app_server.entity.Medication;
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
import static com.example.hospital_app_server.utils.TestEntityFactory.getValidMedication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MedicationControllerSecurityITest {
    private static final String BASE_PATH = "/medications";
    private static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());

    private final MockMvc mockMvc;
    private final int id = 10001;

    @Autowired
    public MedicationControllerSecurityITest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    // GET ALL ---------------------------------------------------------------------------------------------------------

    @Test
    void when_notAuthenticatedUserIsSendingGetAllRequest_then_401isReturned() throws Exception {
        mockMvc.perform(get(BASE_PATH)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "DOCTOR")
    void when_authorizedUserIsSendingGetAllRequest_then_200isReturned() throws Exception {
        mockMvc.perform(get(BASE_PATH)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // GET -------------------------------------------------------------------------------------------------------------

    @Test
    void when_notAuthenticatedUserIsSendingGetRequest_then_401isReturned() throws Exception {
        mockMvc.perform(get(BASE_PATH + "/{id}", id)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "DOCTOR")
    void when_authorizedUserIsSendingGetRequest_then_200isReturned() throws Exception {
        mockMvc.perform(get(BASE_PATH + "/{id}", id)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // POST ------------------------------------------------------------------------------------------------------------

    @Test
    @Transactional
    void when_notAuthenticatedUserIsSendingPostRequest_then_401isReturned() throws Exception {
        Medication createdMedication = getValidMedication(null);

        mockMvc.perform(post(BASE_PATH)
                        .content(convertToJsonString(createdMedication))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Transactional
    @WithMockUser(roles = "DOCTOR")
    void when_authorizedUserIsSendingPostRequest_then_201isReturned() throws Exception {
        Medication createdMedication = getValidMedication(null);

        mockMvc.perform(post(BASE_PATH)
                        .content(convertToJsonString(createdMedication))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    // PUT -------------------------------------------------------------------------------------------------------------

    @Test
    @Transactional
    void when_notAuthenticatedUserIsSendingPutRequest_then_401isReturned() throws Exception {
        Medication updatedMedication = getValidMedication(null);
        updatedMedication.setId(id);

        mockMvc.perform(put(BASE_PATH, id)
                        .content(convertToJsonString(updatedMedication))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Transactional
    @WithMockUser(roles = "DOCTOR")
    void when_authorizedUserIsSendingPutRequest_then_200isReturned() throws Exception {
        Medication updatedMedication = getValidMedication(null);
        updatedMedication.setId(id);

        mockMvc.perform(put(BASE_PATH, id)
                        .content(convertToJsonString(updatedMedication))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // DELETE ----------------------------------------------------------------------------------------------------------

    @Test
    @Transactional
    void when_notAuthenticatedUserIsSendingDeleteRequest_then_401isReturned() throws Exception {
        mockMvc.perform(delete(BASE_PATH + "/{id}", id))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Transactional
    @WithMockUser(roles = "DOCTOR")
    void when_authorizedUserIsSendingDeleteRequest_then_200isReturned() throws Exception {
        mockMvc.perform(delete(BASE_PATH + "/{id}", id))
                .andExpect(status().isOk());
    }
}

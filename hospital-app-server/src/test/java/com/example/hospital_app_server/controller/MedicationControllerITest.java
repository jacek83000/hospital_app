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
public class MedicationControllerITest {
    private static final String BASE_PATH = "/medications";
    private static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());

    private final MockMvc mockMvc;
    private final int id = 10001;

    @Autowired
    public MedicationControllerITest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void when_allMedicationsAreRetrieved_then_allMedicationsAreReturned() throws Exception {
        mockMvc.perform(get(BASE_PATH)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void when_medicationIsRetrievedById_then_medicationIsReturned() throws Exception {
        mockMvc.perform(get(BASE_PATH + "/{id}", id)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("name1")))
                .andExpect(jsonPath("$.companyId", is(1)))
                .andExpect(jsonPath("$.companyName", notNullValue()))
                .andExpect(jsonPath("$.companyWebsite", notNullValue()))
                .andExpect(jsonPath("$.companyContactNumber", notNullValue()))
                .andExpect(jsonPath("$.price", is(39.99)))
                .andExpect(jsonPath("$.description", is("description1")));
    }

    @Test
    @Transactional
    void when_medicationIsCreated_then_medicationIsPersistedInDatabase() throws Exception {
        Medication createdMedication = getValidMedication(null);

        mockMvc.perform(post(BASE_PATH)
                        .content(convertToJsonString(createdMedication))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name", is(createdMedication.getName())))
                .andExpect(jsonPath("$.companyId", is(createdMedication.getCompanyId())))
                .andExpect(jsonPath("$.price", is(createdMedication.getPrice())))
                .andExpect(jsonPath("$.description", is(createdMedication.getDescription())));
    }

    @Test
    @Transactional
    void when_medicationIsUpdated_then_medicationIsModifiedInDatabase() throws Exception {
        Medication updatedMedication = getValidMedication(null);
        updatedMedication.setId(id);

        mockMvc.perform(put(BASE_PATH, id)
                        .content(convertToJsonString(updatedMedication))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updatedMedication.getId())))
                .andExpect(jsonPath("$.name", is(updatedMedication.getName())))
                .andExpect(jsonPath("$.companyId", is(updatedMedication.getCompanyId())))
                .andExpect(jsonPath("$.price", is(updatedMedication.getPrice())))
                .andExpect(jsonPath("$.description", is(updatedMedication.getDescription())));
    }

    @Test
    @Transactional
    void when_medicationIsDeletedById_then_medicationIsRemovedFromDatabase() throws Exception {
        mockMvc.perform(delete(BASE_PATH + "/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

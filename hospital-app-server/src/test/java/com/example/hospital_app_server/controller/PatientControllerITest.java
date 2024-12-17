package com.example.hospital_app_server.controller;

import com.example.hospital_app_server.entity.Patient;
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
import static com.example.hospital_app_server.utils.TestEntityFactory.getValidPatient;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
public class PatientControllerITest {
    private static final String BASE_PATH = "/patients";
    private static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());

    private final int id = 10003;
    private final MockMvc mockMvc;

    @Autowired
    public PatientControllerITest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void when_allPatientsAreRetrieved_then_allPatientsAreReturned() throws Exception {
        mockMvc.perform(get(BASE_PATH)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void when_patientIsRetrievedById_then_patientIsReturned() throws Exception {
        mockMvc.perform(get(BASE_PATH + "/{id}", id)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("firstName3")))
                .andExpect(jsonPath("$.lastName", is("lastName3")))
                .andExpect(jsonPath("$.email", is("patient1@hospital.com")))
                .andExpect(jsonPath("$.contactNumber", is("+48 111 111 113")))
                .andExpect(jsonPath("$.age", is(30)))
                .andExpect(jsonPath("$.address", is("address1")))
                .andExpect(jsonPath("$.sex", is("male")));
    }

    @Test
    @Transactional
    void when_patientIsCreated_then_patientIsPersistedInDatabase() throws Exception {
        Patient createdPatient = getValidPatient(null);

        mockMvc.perform(post(BASE_PATH)
                        .content(convertToJsonString(createdPatient))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName", is(createdPatient.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(createdPatient.getLastName())))
                .andExpect(jsonPath("$.email", is(createdPatient.getEmail())))
                .andExpect(jsonPath("$.contactNumber", is(createdPatient.getContactNumber())))
                .andExpect(jsonPath("$.age", is(createdPatient.getAge())))
                .andExpect(jsonPath("$.address", is(createdPatient.getAddress())))
                .andExpect(jsonPath("$.sex", is(createdPatient.getSex())));
    }

    @Test
    @Transactional
    void when_patientIsUpdated_then_patientIsModifiedInDatabase() throws Exception {
        Patient updatedPatient = getValidPatient(null);
        updatedPatient.setId(id);

        mockMvc.perform(put(BASE_PATH, id)
                        .content(convertToJsonString(updatedPatient))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updatedPatient.getId())))
                .andExpect(jsonPath("$.firstName", is(updatedPatient.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedPatient.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedPatient.getEmail())))
                .andExpect(jsonPath("$.contactNumber", is(updatedPatient.getContactNumber())))
                .andExpect(jsonPath("$.age", is(updatedPatient.getAge())))
                .andExpect(jsonPath("$.address", is(updatedPatient.getAddress())))
                .andExpect(jsonPath("$.sex", is(updatedPatient.getSex())));
    }

    @Test
    @Transactional
    void when_patientIsDeletedById_then_patientIsRemovedFromDatabase() throws Exception {
        mockMvc.perform(delete(BASE_PATH + "/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

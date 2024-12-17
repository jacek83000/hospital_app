package com.example.hospital_app_server.controller;

import com.example.hospital_app_server.entity.Doctor;
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
import static com.example.hospital_app_server.utils.TestEntityFactory.getValidDoctor;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
public class DoctorControllerITest {
    private static final String BASE_PATH = "/doctors";
    private static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());

    private final int id = 10001;
    private final MockMvc mockMvc;

    @Autowired
    public DoctorControllerITest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void when_allDoctorsAreRetrieved_then_allDoctorsAreReturned() throws Exception {
        mockMvc.perform(get(BASE_PATH)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void when_doctorIsRetrievedById_then_doctorIsReturned() throws Exception {
        mockMvc.perform(get(BASE_PATH + "/{id}", id)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("firstName1")))
                .andExpect(jsonPath("$.lastName", is("lastName1")))
                .andExpect(jsonPath("$.email", is("doctor1@hospital.com")))
                .andExpect(jsonPath("$.contactNumber", is("+48 111 111 111")))
                .andExpect(jsonPath("$.specialization", is("specialization1")))
                .andExpect(jsonPath("$.yearsOfExperience", is(19)));
    }

    @Test
    @Transactional
    void when_doctorIsCreated_then_doctorIsPersistedInDatabase() throws Exception {
        Doctor createdDoctor = getValidDoctor(null);

        mockMvc.perform(post(BASE_PATH)
                        .content(convertToJsonString(createdDoctor))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName", is(createdDoctor.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(createdDoctor.getLastName())))
                .andExpect(jsonPath("$.email", is(createdDoctor.getEmail())))
                .andExpect(jsonPath("$.contactNumber", is(createdDoctor.getContactNumber())))
                .andExpect(jsonPath("$.specialization", is(createdDoctor.getSpecialization())))
                .andExpect(jsonPath("$.yearsOfExperience", is(createdDoctor.getYearsOfExperience())));
    }

    @Test
    @Transactional
    void when_doctorIsUpdated_then_doctorIsModifiedInDatabase() throws Exception {
        Doctor updatedDoctor = getValidDoctor(null);
        updatedDoctor.setId(id);

        mockMvc.perform(put(BASE_PATH, id)
                        .content(convertToJsonString(updatedDoctor))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updatedDoctor.getId())))
                .andExpect(jsonPath("$.firstName", is(updatedDoctor.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedDoctor.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedDoctor.getEmail())))
                .andExpect(jsonPath("$.contactNumber", is(updatedDoctor.getContactNumber())))
                .andExpect(jsonPath("$.specialization", is(updatedDoctor.getSpecialization())))
                .andExpect(jsonPath("$.yearsOfExperience", is(updatedDoctor.getYearsOfExperience())));
    }

    @Test
    @Transactional
    void when_doctorIsDeletedById_then_doctorIsRemovedFromDatabase() throws Exception {
        mockMvc.perform(delete(BASE_PATH + "/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }
}


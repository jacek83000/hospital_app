package com.example.hospital_app_server.controller;


import com.example.hospital_app_server.dto.request.ReceiptCreateReqDTO;
import com.example.hospital_app_server.dto.request.ReceiptUpdateReqDTO;
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
import static com.example.hospital_app_server.utils.TestEntityDTOFactory.getValidReceiptCreateReqDTO;
import static com.example.hospital_app_server.utils.TestEntityDTOFactory.getValidReceiptUpdateReqDTO;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
public class ReceiptControllerITest {
    private static final String BASE_PATH = "/receipts";
    private static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());

    private final int id = 10001;
    private final MockMvc mockMvc;

    @Autowired
    public ReceiptControllerITest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void when_allReceiptsAreRetrieved_then_allReceiptsAreReturned() throws Exception {
        mockMvc.perform(get(BASE_PATH)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void when_receiptIsRetrievedById_then_receiptIsReturned() throws Exception {
        mockMvc.perform(get(BASE_PATH + "/{id}", id)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.createdAt", is(notNullValue())))
                .andExpect(jsonPath("$.expirationDate", is(notNullValue())))
                .andExpect(jsonPath("$.medications", is(notNullValue())));
    }

    @Test
    void when_receiptIsCreated_then_receiptIsPersistedInDatabase() throws Exception {
        ReceiptCreateReqDTO reqDTO = getValidReceiptCreateReqDTO();

        mockMvc.perform(post(BASE_PATH)
                        .content(convertToJsonString(reqDTO))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.createdAt", is(notNullValue())))
                .andExpect(jsonPath("$.expirationDate", is(notNullValue())))
                .andExpect(jsonPath("$.medications", is(notNullValue())));
    }

    @Test
    @Transactional
    void when_receiptIsUpdated_then_receiptIsModifiedInDatabase() throws Exception {
        ReceiptUpdateReqDTO reqDTO = getValidReceiptUpdateReqDTO(id);

        mockMvc.perform(put(BASE_PATH, id)
                        .content(convertToJsonString(reqDTO))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(reqDTO.getId())))
                .andExpect(jsonPath("$.createdAt", is(notNullValue())))
                .andExpect(jsonPath("$.expirationDate", is(notNullValue())))
                .andExpect(jsonPath("$.medications", is(notNullValue())));
    }

    @Test
    @Transactional
    void when_receiptIsDeletedById_then_receiptIsRemovedFromDatabase() throws Exception {
        mockMvc.perform(delete(BASE_PATH + "/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

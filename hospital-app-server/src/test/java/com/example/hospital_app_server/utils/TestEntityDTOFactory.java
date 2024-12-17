package com.example.hospital_app_server.utils;

import com.example.hospital_app_server.dto.request.ReceiptCreateReqDTO;
import com.example.hospital_app_server.dto.request.ReceiptUpdateReqDTO;
import com.example.hospital_app_server.dto.request.VisitCreateReqDTO;
import com.example.hospital_app_server.dto.request.VisitUpdateReqDTO;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public final class TestEntityDTOFactory {
    private TestEntityDTOFactory() {

    }

    public static ReceiptCreateReqDTO getValidReceiptCreateReqDTO() {
        ReceiptCreateReqDTO reqDTO = new ReceiptCreateReqDTO();
        reqDTO.setVisitId(10002);
        Set<Integer> medicationIds = new HashSet<>();
        medicationIds.add(10001);
        medicationIds.add(10002);
        reqDTO.setMedicationIds(medicationIds);

        return reqDTO;
    }

    public static ReceiptUpdateReqDTO getValidReceiptUpdateReqDTO(int id) {
        ReceiptUpdateReqDTO reqDTO = new ReceiptUpdateReqDTO();
        reqDTO.setId(id);
        reqDTO.setVisitId(10002);
        Set<Integer> medicationIds = new HashSet<>();
        medicationIds.add(10001);
        medicationIds.add(10002);
        reqDTO.setMedicationIds(medicationIds);

        return reqDTO;
    }

    public static VisitCreateReqDTO getValidVisitCreateReqDTO() {
        VisitCreateReqDTO reqDTO = new VisitCreateReqDTO();
        reqDTO.setDate(LocalDateTime.now().plusDays(10));
        reqDTO.setAssurance(true);
        reqDTO.setPrice(129.0);
        reqDTO.setDoctorId(10001);
        reqDTO.setPatientId(10003);

        return reqDTO;
    }

    public static VisitUpdateReqDTO getValidVisitUpdateReqDTO(int id) {
        VisitUpdateReqDTO reqDTO = new VisitUpdateReqDTO();
        reqDTO.setId(id);
        reqDTO.setDate(LocalDateTime.now().plusDays(10));
        reqDTO.setAssurance(false);
        reqDTO.setPrice(171.9);
        reqDTO.setDoctorId(10001);
        reqDTO.setPatientId(10003);

        return reqDTO;
    }
}

package com.example.hospital_app_server.dto.mapper;


import com.example.hospital_app_server.dto.request.ReceiptNestedCreateReqDTO;
import com.example.hospital_app_server.dto.request.VisitCreateReqDTO;
import com.example.hospital_app_server.dto.request.VisitUpdateReqDTO;
import com.example.hospital_app_server.entity.Doctor;
import com.example.hospital_app_server.entity.Patient;
import com.example.hospital_app_server.entity.Receipt;
import com.example.hospital_app_server.entity.Visit;
import com.example.hospital_app_server.service.DoctorService;
import com.example.hospital_app_server.service.PatientService;
import com.example.hospital_app_server.service.VisitService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VisitMapper {
    private final VisitService visitService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final ReceiptMapper receiptMapper;

    public VisitMapper(VisitService visitService, DoctorService doctorService, PatientService patientService, ReceiptMapper receiptMapper) {
        this.visitService = visitService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.receiptMapper = receiptMapper;
    }

    public Visit toEntity(VisitCreateReqDTO dto) {
        Visit visit = new Visit();
        BeanUtils.copyProperties(dto, visit);

        List<ReceiptNestedCreateReqDTO> receiptsDTO = dto.getReceiptsDTO();
        if (receiptsDTO != null) {
            List<Receipt> receipts = receiptsDTO.stream()
                    .map(receiptMapper::toEntity)
                    .peek(receipt -> receipt.setVisit(visit))
                    .toList();
            visit.setReceipts(receipts);
            visit.getReceipts().forEach(receipt -> receipt.setVisit(visit));
        }

        Doctor doctor = doctorService.findById(dto.getDoctorId());
        Patient patient = patientService.findById(dto.getPatientId());
        visit.setDoctor(doctor);
        visit.setPatient(patient);
        doctor.getVisits().add(visit);
        patient.getVisits().add(visit);
        return visit;
    }

    public Visit toEntity(VisitUpdateReqDTO dto) {
        Visit visit = new Visit();
        BeanUtils.copyProperties(dto, visit);

        Visit preUpdateVisit = visitService.findById(dto.getId());
        visit.setReceipts(preUpdateVisit.getReceipts());

        Doctor doctor = doctorService.findById(dto.getDoctorId());
        Patient patient = patientService.findById(dto.getPatientId());

        visit.setDoctor(doctor);
        visit.setPatient(patient);
        doctor.getVisits().add(visit);
        patient.getVisits().add(visit);

        return visit;
    }
}

package com.example.hospital_app_server.dto.mapper;

import com.example.hospital_app_server.dto.request.ReceiptCreateReqDTO;
import com.example.hospital_app_server.dto.request.ReceiptNestedCreateReqDTO;
import com.example.hospital_app_server.dto.request.ReceiptUpdateReqDTO;
import com.example.hospital_app_server.entity.Medication;
import com.example.hospital_app_server.entity.Receipt;
import com.example.hospital_app_server.entity.Visit;
import com.example.hospital_app_server.service.MedicationService;
import com.example.hospital_app_server.service.ReceiptService;
import com.example.hospital_app_server.service.VisitService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ReceiptMapper {
    private final ReceiptService receiptService;
    private final MedicationService medicationService;
    private final VisitService visitService;

    public ReceiptMapper(ReceiptService receiptService, MedicationService medicationService, VisitService visitService) {
        this.receiptService = receiptService;
        this.medicationService = medicationService;
        this.visitService = visitService;
    }

    public Receipt toEntity(ReceiptNestedCreateReqDTO dto) {
        Receipt receipt = new Receipt();
        BeanUtils.copyProperties(dto, receipt);
        for (Integer mId : dto.getMedicationIds()) {
            Medication medication = medicationService.findById(mId);
            receipt.getMedications().add(medication);
            medication.getReceipts().add(receipt);
        }
        return receipt;
    }

    public Receipt toEntity(ReceiptCreateReqDTO dto) {
        Receipt receipt = new Receipt();
        BeanUtils.copyProperties(dto, receipt);
        for (Integer mId : dto.getMedicationIds()) {
            Medication medication = medicationService.findById(mId);
            receipt.getMedications().add(medication);
            medication.getReceipts().add(receipt);
        }

        Visit visit = visitService.findById(dto.getVisitId());
        receipt.setVisit(visit);

        return receipt;
    }

    public Receipt toEntity(ReceiptUpdateReqDTO dto) {
        Receipt receipt = new Receipt();
        BeanUtils.copyProperties(dto, receipt);
        for (Integer mId : dto.getMedicationIds()) {
            Medication medication = medicationService.findById(mId);
            receipt.getMedications().add(medication);
            medication.getReceipts().add(receipt);
        }

        Receipt oldReceipt = receiptService.findById(dto.getId());
        receipt.setCreatedAt(oldReceipt.getCreatedAt());
        receipt.setExpirationDate(oldReceipt.getExpirationDate());

        Visit visit = visitService.findById(dto.getVisitId());
        receipt.setVisit(visit);

        return receipt;
    }
}

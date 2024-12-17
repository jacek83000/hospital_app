package com.example.hospital_app_server.dto.request;

import com.example.hospital_app_server.entity.Medication;
import com.example.hospital_app_server.entity.Visit;
import com.example.hospital_app_server.validation.IdExists;
import com.example.hospital_app_server.validation.IdsExist;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class ReceiptUpdateReqDTO {
    @PositiveOrZero(message = "{messages.validation.positive-or-zero}")
    private int id;

    @IdsExist(entityType = Medication.class, message = "{messages.validation.ids}")
    @Size(min = 1, max = 12, message = "{messages.validation.size}")
    private Set<Integer> medicationIds;

    @IdExists(entityType = Visit.class, message = "{messages.validation.id}")
    private int visitId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Integer> getMedicationIds() {
        return medicationIds;
    }

    public void setMedicationIds(@Size(min = 1, max = 12, message = "{messages.validation.size}") Set<Integer> medicationIds) {
        this.medicationIds = medicationIds;
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }
}

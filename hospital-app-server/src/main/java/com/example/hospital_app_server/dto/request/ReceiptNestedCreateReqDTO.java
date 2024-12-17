package com.example.hospital_app_server.dto.request;

import com.example.hospital_app_server.entity.Medication;
import com.example.hospital_app_server.validation.IdsExist;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class ReceiptNestedCreateReqDTO {
    @IdsExist(entityType = Medication.class, message = "{messages.validation.ids}")
    @Size(min = 1, max = 12, message = "{messages.validation.size}")
    private Set<Integer> medicationIds;

    public @Size(min = 1, max = 12, message = "{messages.validation.size}") Set<Integer> getMedicationIds() {
        return medicationIds;
    }

    public void setMedicationIds(@Size(min = 1, max = 12, message = "{messages.validation.size}") Set<Integer> medicationIds) {
        this.medicationIds = medicationIds;
    }
}

package com.example.hospital_app_server.dto.request;

import com.example.hospital_app_server.entity.Doctor;
import com.example.hospital_app_server.entity.Patient;
import com.example.hospital_app_server.validation.DecimalRange;
import com.example.hospital_app_server.validation.IdExists;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class VisitCreateReqDTO {
    @NotNull(message = "{messages.validation.notnull}")
    private LocalDateTime date;

    @NotNull(message = "{messages.validation.notnull}")
    private boolean assurance;

    @DecimalRange(max = 100_000.0, message = "{messages.validation.range}")
    private double price;

    @JsonProperty("receipts")
    @Size(max = 12, message = "{messages.validation.size}")
    @Valid
    private List<ReceiptNestedCreateReqDTO> receiptsDTO;

    @IdExists(entityType = Patient.class, message = "{messages.validation.id}")
    private int patientId;

    @IdExists(entityType = Doctor.class, message = "{messages.validation.id}")
    private int doctorId;

    public @NotNull(message = "{messages.validation.notnull}") LocalDateTime getDate() {
        return date;
    }

    public void setDate(@NotNull(message = "{messages.validation.notnull}") LocalDateTime date) {
        this.date = date;
    }

    @NotNull(message = "{messages.validation.notnull}")
    public boolean isAssurance() {
        return assurance;
    }

    public void setAssurance(@NotNull(message = "{messages.validation.notnull}") boolean assurance) {
        this.assurance = assurance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public @Size(max = 12, message = "{messages.validation.size}") @Valid List<ReceiptNestedCreateReqDTO> getReceiptsDTO() {
        return receiptsDTO;
    }

    public void setReceiptsDTO(@Size(max = 12, message = "{messages.validation.size}") @Valid List<ReceiptNestedCreateReqDTO> receiptsDTO) {
        this.receiptsDTO = receiptsDTO;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
}

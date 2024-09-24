package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Medication;

import java.util.List;

public interface MedicationService {
    Medication findById(int id);

    List<Medication> findAll();

    Medication create(Medication medication);

    Medication update(Medication medication);

    void deleteById(int id);
}

package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Medication;

import java.util.List;
import java.util.Optional;

public interface MedicationService {
    Optional<Medication> findById(int id);

    List<Medication> findAll();

    void save(Medication medication);

    void deleteById(int id);
}

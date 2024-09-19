package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Optional<Patient> findById(int id);

    List<Patient> findAll();

    Patient save(Patient patient);

    void deleteById(int id);
}

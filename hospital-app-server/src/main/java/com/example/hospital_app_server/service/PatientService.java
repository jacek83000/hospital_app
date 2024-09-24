package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Patient;

import java.util.List;

public interface PatientService {
    Patient findById(int id);

    List<Patient> findAll();

    Patient create(Patient patient);

    Patient update(Patient patient);

    void deleteById(int id);
}

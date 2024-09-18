package com.example.hospital_app_server.repository;

import com.example.hospital_app_server.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}

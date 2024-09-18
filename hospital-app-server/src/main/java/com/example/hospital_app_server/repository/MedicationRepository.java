package com.example.hospital_app_server.repository;

import com.example.hospital_app_server.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
}

package com.example.hospital_app_server.repository;

import com.example.hospital_app_server.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}

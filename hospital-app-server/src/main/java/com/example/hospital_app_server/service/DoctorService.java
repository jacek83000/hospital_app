package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Optional<Doctor> findById(int id);

    List<Doctor> findAll();

    void save(Doctor doctor);

    void deleteById(int id);
}

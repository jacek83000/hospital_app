package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor findById(int id);

    List<Doctor> findAll();

    Doctor create(Doctor doctor);

    Doctor update(Doctor doctor);

    void deleteById(int id);
}

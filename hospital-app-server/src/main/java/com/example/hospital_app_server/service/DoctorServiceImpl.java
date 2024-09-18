package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Doctor;
import com.example.hospital_app_server.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository repository;

    public DoctorServiceImpl(DoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Doctor> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Doctor> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void save(Doctor doctor) {
        repository.save(doctor);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}

package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Patient;
import com.example.hospital_app_server.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository repository;

    public PatientServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Patient> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Patient> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Patient save(Patient patient) {
        return repository.save(patient);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}

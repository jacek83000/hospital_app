package com.example.hospital_app_server.service.impl;

import com.example.hospital_app_server.entity.Patient;
import com.example.hospital_app_server.exception.ResourceNotFoundException;
import com.example.hospital_app_server.repository.PatientRepository;
import com.example.hospital_app_server.service.PatientService;
import com.example.hospital_app_server.utils.MessageUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository repository;

    public PatientServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Patient findById(int id) {
        String message = MessageUtil.getMessage("messages.resource.patient.not-found", id);
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(message));
    }

    @Override
    public List<Patient> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Patient create(Patient patient) {
        patient.setId(0);
        return repository.save(patient);
    }

    @Transactional
    @Override
    public Patient update(Patient patient) {
        findById(patient.getId());
        return repository.save(patient);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        findById(id);
        repository.deleteById(id);
    }
}

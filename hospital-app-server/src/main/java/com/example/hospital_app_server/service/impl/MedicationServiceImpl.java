package com.example.hospital_app_server.service.impl;

import com.example.hospital_app_server.entity.Medication;
import com.example.hospital_app_server.exception.ResourceNotFoundException;
import com.example.hospital_app_server.repository.MedicationRepository;
import com.example.hospital_app_server.service.MedicationService;
import com.example.hospital_app_server.utils.MessageUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService {
    private final MedicationRepository repository;

    public MedicationServiceImpl(MedicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Medication findById(int id) {
        String message = MessageUtil.getMessage("messages.resource.medication.not-found", id);
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(message));
    }

    @Override
    public List<Medication> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Medication create(Medication medication) {
        medication.setId(0);
        return repository.save(medication);
    }

    @Transactional
    @Override
    public Medication update(Medication medication) {
        findById(medication.getId());
        return repository.save(medication);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        findById(id);
        repository.deleteById(id);
    }
}

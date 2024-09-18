package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Medication;
import com.example.hospital_app_server.repository.MedicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationServiceImpl implements MedicationService {
    private final MedicationRepository repository;

    public MedicationServiceImpl(MedicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Medication> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Medication> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void save(Medication medication) {
        repository.save(medication);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}

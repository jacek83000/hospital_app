package com.example.hospital_app_server.service.impl;

import com.example.hospital_app_server.entity.Doctor;
import com.example.hospital_app_server.exception.ResourceNotFoundException;
import com.example.hospital_app_server.repository.DoctorRepository;
import com.example.hospital_app_server.service.DoctorService;
import com.example.hospital_app_server.utils.MessageUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository repository;

    public DoctorServiceImpl(DoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Doctor findById(int id) {
        String message = MessageUtil.getMessage("messages.resource.doctor.not-found", id);
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(message));
    }

    @Override
    public List<Doctor> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Doctor create(Doctor doctor) {
        doctor.setId(0);
        return repository.save(doctor);
    }

    @Transactional
    @Override
    public Doctor update(Doctor doctor) {
        findById(doctor.getId());
        return repository.save(doctor);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        findById(id);
        repository.deleteById(id);
    }
}

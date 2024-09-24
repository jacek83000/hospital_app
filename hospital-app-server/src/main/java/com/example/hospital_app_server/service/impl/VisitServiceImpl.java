package com.example.hospital_app_server.service.impl;

import com.example.hospital_app_server.entity.Visit;
import com.example.hospital_app_server.exception.ResourceNotFoundException;
import com.example.hospital_app_server.repository.VisitRepository;
import com.example.hospital_app_server.service.VisitService;
import com.example.hospital_app_server.utils.MessageUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {
    private final VisitRepository repository;

    public VisitServiceImpl(VisitRepository repository) {
        this.repository = repository;
    }

    @Override
    public Visit findById(int id) {
        String message = MessageUtil.getMessage("messages.resource.visit.not-found", id);
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(message));
    }

    @Override
    public List<Visit> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Visit create(Visit visit) {
        visit.setId(0);
        return repository.save(visit);
    }

    @Transactional
    @Override
    public Visit update(Visit visit) {
        findById(visit.getId());
        return repository.save(visit);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        findById(id);
        repository.deleteById(id);
    }
}

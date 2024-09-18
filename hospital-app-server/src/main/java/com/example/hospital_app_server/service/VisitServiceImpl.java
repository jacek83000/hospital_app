package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Visit;
import com.example.hospital_app_server.repository.VisitRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitServiceImpl implements VisitService {
    private final VisitRepository repository;

    public VisitServiceImpl(VisitRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Visit> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Visit> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void save(Visit visit) {
        repository.save(visit);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}

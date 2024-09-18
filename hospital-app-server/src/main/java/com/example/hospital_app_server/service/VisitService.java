package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Visit;

import java.util.List;
import java.util.Optional;

public interface VisitService {
    Optional<Visit> findById(int id);

    List<Visit> findAll();

    void save(Visit visit);

    void deleteById(int id);
}

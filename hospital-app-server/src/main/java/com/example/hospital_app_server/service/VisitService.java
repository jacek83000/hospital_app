package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Visit;

import java.util.List;

public interface VisitService {
    Visit findById(int id);

    List<Visit> findAll();

    Visit create(Visit visit);

    Visit update(Visit visit);

    void deleteById(int id);
}

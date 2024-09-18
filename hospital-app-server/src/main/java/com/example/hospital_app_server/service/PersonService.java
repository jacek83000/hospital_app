package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Optional<Person> findById(int id);

    List<Person> findAll();
}

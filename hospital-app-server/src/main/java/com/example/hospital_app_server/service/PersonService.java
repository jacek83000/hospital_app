package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Person;

import java.util.List;

public interface PersonService {
    Person findById(int id);

    List<Person> findAll();
}

package com.example.hospital_app_server.service.impl;

import com.example.hospital_app_server.entity.Person;
import com.example.hospital_app_server.exception.ResourceNotFoundException;
import com.example.hospital_app_server.repository.PersonRepository;
import com.example.hospital_app_server.service.PersonService;
import com.example.hospital_app_server.utils.MessageUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person findById(int id) {
        String message = MessageUtil.getMessage("messages.resource.person.not-found", id);
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(message));
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }
}

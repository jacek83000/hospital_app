package com.example.hospital_app_server.repository;

import com.example.hospital_app_server.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}

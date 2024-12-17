package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Person;
import com.example.hospital_app_server.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonServiceITest {
    private final PersonRepository personRepository;
    private final int id = 10001;

    @Autowired
    public PersonServiceITest(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Test
    void when_allPeopleAreRetrieved_then_allPersonsAreReturned() {
        List<Person> people = personRepository.findAll();
        assertThat(people.size()).isGreaterThan(0);
    }

    @Test
    void when_personIsRetrievedById_then_personIsReturned() {
        Optional<Person> optionalPerson = personRepository.findById(id);
        assertTrue(optionalPerson.isPresent());

        Person foundPerson = optionalPerson.get();
        assertThat(foundPerson.getId()).isEqualTo(id);
    }
}

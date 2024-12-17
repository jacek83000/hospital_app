package com.example.hospital_app_server.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PersonTest {
    private Validator validator;
    private Doctor person;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        person = new Doctor();
        person.setSpecialization("string");
        person.setYearsOfExperience(20);
        person.setVisits(null);

        person.setFirstName("string");
        person.setLastName("string");
        person.setEmail("string@gmail.com");
        person.setContactNumber("string");
    }

    @Test
    void when_personWithValidFieldsIsValidated_then_zeroViolationsAreReturned() {
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    void when_personWithInvalidNotBlankFieldsIsValidated_then_fourViolationsAreReturned() {
        person.setFirstName("\n");
        person.setLastName("");
        person.setEmail("");
        person.setContactNumber("\t");

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertThat(violations.size()).isEqualTo(4);
    }

    @Test
    void when_personWithInvalidEmailFieldsIsValidated_then_oneViolationIsReturned() {
        person.setEmail("string");

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertThat(violations.size()).isEqualTo(1);
    }
}

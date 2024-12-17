package com.example.hospital_app_server.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static com.example.hospital_app_server.utils.TestEntityFactory.getValidDoctor;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PatientTest {
    private Validator validator;
    private Patient patient;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        patient = new Patient();
        patient.setFirstName("string");
        patient.setLastName("string");
        patient.setEmail("string@hospital.com");
        patient.setContactNumber("string");
        patient.setAge(30);
        patient.setSex("male");
        patient.setAddress("string");
    }

    @Test
    void when_patientWithValidFieldsIsValidated_then_zeroViolationsAreReturned() {
        Set<ConstraintViolation<Patient>> violations = validator.validate(patient);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    void when_patientWithInvalidNotNullFieldsIsValidated_then_oneViolationIsReturned() {
        patient.setSex(null);

        Set<ConstraintViolation<Patient>> violations = validator.validate(patient);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    void when_patientWithInvalidNotBlankFieldsIsValidated_then_oneViolationIsReturned() {
        patient.setAddress("");

        Set<ConstraintViolation<Patient>> violations = validator.validate(patient);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    void when_patientWithValueBelowRangeFieldsIsValidated_then_oneViolationIsReturned() {
        patient.setAge(-1_000_000);

        Set<ConstraintViolation<Patient>> violations = validator.validate(patient);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    void when_patientWithValueAboveRangeFieldsIsValidated_then_oneViolationIsReturned() {
        patient.setAge(1_000_000);

        Set<ConstraintViolation<Patient>> violations = validator.validate(patient);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    void when_patientWithInvalidSexFieldsIsValidated_then_oneViolationIsReturned() {
        patient.setSex("string");

        Set<ConstraintViolation<Patient>> violations = validator.validate(patient);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    void when_patientWithValidVisitsFieldsIsValidated_then_zeroViolationsAreReturned() {
        List<Visit> visits = IntStream.range(0, 5).boxed().map(_ -> {
            Visit visit = new Visit();
            visit.setDate(LocalDateTime.now());
            visit.setAssurance(true);
            visit.setPrice(100.0);

            visit.setDoctor(getValidDoctor(visit));
            visit.setPatient(patient);
            return visit;
        }).toList();
        patient.setVisits(visits);

        Set<ConstraintViolation<Patient>> violations = validator.validate(patient);
        assertThat(violations.size()).isEqualTo(0);
    }
}

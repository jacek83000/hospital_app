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

import static com.example.hospital_app_server.utils.TestEntityFactory.getValidPatient;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DoctorTest {
    private Validator validator;
    private Doctor doctor;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        doctor = new Doctor();
        doctor.setFirstName("string");
        doctor.setLastName("string");
        doctor.setEmail("string@gmail.com");
        doctor.setContactNumber("string");
        doctor.setSpecialization("string");
        doctor.setYearsOfExperience(20);
    }

    @Test
    void when_doctorWithValidFieldsIsValidated_then_zeroViolationsAreReturned() {
        Set<ConstraintViolation<Doctor>> violations = validator.validate(doctor);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    void when_doctorWithInvalidNotBlankFieldsIsValidated_then_oneViolationIsReturned() {
        doctor.setSpecialization("");

        Set<ConstraintViolation<Doctor>> violations = validator.validate(doctor);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    void when_doctorWithValueBelowRangeFieldsIsValidated_then_oneViolationIsReturned() {
        doctor.setYearsOfExperience(-1_000_000);

        Set<ConstraintViolation<Doctor>> violations = validator.validate(doctor);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    void when_doctorWithValueAboveRangeFieldsIsValidated_then_oneViolationIsReturned() {
        doctor.setYearsOfExperience(1_000_000);

        Set<ConstraintViolation<Doctor>> violations = validator.validate(doctor);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    void when_doctorWithValidVisitsFieldsIsValidated_then_zeroViolationsAreReturned() {
        List<Visit> visits = IntStream.range(0, 5).boxed().map(_ -> {
            Visit visit = new Visit();
            visit.setDate(LocalDateTime.now());
            visit.setAssurance(true);
            visit.setPrice(100.0);

            visit.setDoctor(doctor);
            visit.setPatient(getValidPatient(visit));
            return visit;
        }).toList();
        doctor.setVisits(visits);

        Set<ConstraintViolation<Doctor>> violations = validator.validate(doctor);
        assertThat(violations.size()).isEqualTo(0);
    }
}

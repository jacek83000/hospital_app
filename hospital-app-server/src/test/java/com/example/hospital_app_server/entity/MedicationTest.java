package com.example.hospital_app_server.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MedicationTest {
    private Validator validator;
    private Medication medication;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        medication = new Medication();
        medication.setName("string");
        medication.setCompanyId(1);
        medication.setPrice(10.5);
    }

    @Test
    public void when_medicationWithValidFieldsIsValidated_then_zeroViolationsAreReturned() {
        Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void when_medicationWithInvalidNotBlankFieldsIsValidated_then_oneViolationIsReturned() {
        medication.setName("");

        Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void when_medicationWithValueBelowPositiveOrZeroIsValidated_then_oneViolationIsReturned() {
        medication.setCompanyId(-1);

        Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void when_medicationWithValueBelowDecimalRangeFieldsIsValidated_then_oneViolationIsReturned() {
        medication.setPrice(-100_000_000.0);

        Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void when_medicationWithValueAboveDecimalRangeFieldsIsValidated_then_oneViolationIsReturned() {
        medication.setPrice(100_000_000.0);

        Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
        assertThat(violations.size()).isEqualTo(1);
    }
}

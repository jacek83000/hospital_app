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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.hospital_app_server.utils.TestEntityFactory.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class VisitTest {
    private Validator validator;
    private Visit visit;

    @BeforeEach
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        visit = new Visit();
        visit.setDate(LocalDateTime.now());
        visit.setAssurance(true);
        visit.setPrice(100.0);
        visit.setDoctor(getValidDoctor(visit));
        visit.setPatient(getValidPatient(visit));
    }

    @Test
    public void when_visitWithValidFieldsIsValidated_then_zeroViolationAreReturned() {
        Set<ConstraintViolation<Visit>> violations = validator.validate(visit);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void when_visitWithInvalidNotNullFieldsIsValidated_then_threeViolationsAreReturned() {
        visit.setDate(null);
        visit.setDoctor(null);
        visit.setPatient(null);

        Set<ConstraintViolation<Visit>> violations = validator.validate(visit);
        assertThat(violations.size()).isEqualTo(3);
    }

    @Test
    void when_visitWithValueBelowRangeFieldsIsValidated_then_oneViolationIsReturned() {
        visit.setPrice(-1_000_000.0);

        Set<ConstraintViolation<Visit>> violations = validator.validate(visit);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void when_visitWithValueAboveRangeFieldsIsValidated_then_oneViolationIsReturned() {
        visit.setPrice(1_000_000.0);

        Set<ConstraintViolation<Visit>> violations = validator.validate(visit);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void when_visitWithValueAboveSizeFieldsIsValidated_then_oneViolationIsReturned() {
        List<Receipt> receipts = IntStream.range(0, 20).boxed().map(_ -> {
            Receipt receipt = new Receipt();
            receipt.getMedications().addAll(IntStream.range(0, 2).boxed().map(_ -> getValidMedication(receipt)).collect(Collectors.toSet()));
            receipt.setVisit(visit);
            return receipt;
        }).toList();
        visit.setReceipts(receipts);

        Set<ConstraintViolation<Visit>> violations = validator.validate(visit);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void when_doctorWithValidReceiptsFieldsIsValidated_then_zeroViolationsAreReturned() {
        List<Receipt> receipts = IntStream.range(0, 3).boxed().map(_ -> {
            Receipt receipt = new Receipt();
            receipt.getMedications().addAll(IntStream.range(0, 2).boxed().map(_ -> getValidMedication(receipt)).collect(Collectors.toSet()));
            receipt.setVisit(visit);
            return receipt;
        }).toList();
        visit.setReceipts(receipts);

        Set<ConstraintViolation<Visit>> violations = validator.validate(visit);
        assertThat(violations.size()).isEqualTo(0);
    }
}

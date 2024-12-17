package com.example.hospital_app_server.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.hospital_app_server.utils.TestEntityFactory.getValidMedication;
import static com.example.hospital_app_server.utils.TestEntityFactory.getValidVisit;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ReceiptTest {
    private Validator validator;
    private Receipt receipt;

    @BeforeEach
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        receipt = new Receipt();
        receipt.setVisit(getValidVisit(receipt));
        receipt.getMedications().add(getValidMedication(receipt));
    }

    @Test
    void when_receiptWithValidFieldsIsValidated_then_zeroViolationAreReturned() {
        Set<ConstraintViolation<Receipt>> violations = validator.validate(receipt);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void when_receiptWithInvalidNotNullFieldsIsValidated_then_oneViolationIsReturned() {
        receipt.setVisit(null);

        Set<ConstraintViolation<Receipt>> violations = validator.validate(receipt);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void when_receiptWithValueBelowSizeFieldsIsValidated_then_oneViolationIsReturned() {
        receipt.getMedications().clear();

        Set<ConstraintViolation<Receipt>> violations = validator.validate(receipt);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void when_receiptWithValueAboveSizeFieldsIsValidated_then_oneViolationIsReturned() {
        receipt.getMedications().addAll(
                IntStream.range(0, 20)
                        .boxed()
                        .map(_ -> getValidMedication(receipt))
                        .collect(Collectors.toSet()));

        Set<ConstraintViolation<Receipt>> violations = validator.validate(receipt);
        assertThat(violations.size()).isEqualTo(1);
    }
}

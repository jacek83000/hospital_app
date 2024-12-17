package com.example.hospital_app_server.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidatableValidator {
    private final Validator validator;

    public ValidatableValidator(Validator validator) {
        this.validator = validator;
    }

    public void validate(Validatable entity) {
        Set<ConstraintViolation<Validatable>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public void validateProperty(Validatable entity, String property) {
        Set<ConstraintViolation<Validatable>> violations = validator.validateProperty(entity, property);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}

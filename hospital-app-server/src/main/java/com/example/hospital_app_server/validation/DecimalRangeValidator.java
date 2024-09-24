package com.example.hospital_app_server.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DecimalRangeValidator implements ConstraintValidator<DecimalRange, Double> {
    private double min;
    private double max;

    @Override
    public void initialize(DecimalRange decimalRange) {
        this.min = decimalRange.min();
        this.max = decimalRange.max();
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return value >= min && value <= max;
    }
}

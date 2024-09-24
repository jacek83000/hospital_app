package com.example.hospital_app_server.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DecimalRangeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DecimalRange {
    String message() default "Field must be between {min} and {max}";

    double min() default 0.0;

    double max() default 10_000.0;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

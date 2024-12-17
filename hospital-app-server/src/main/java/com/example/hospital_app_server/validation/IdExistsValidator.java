package com.example.hospital_app_server.validation;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdExistsValidator implements ConstraintValidator<IdExists, Integer> {
    private final EntityManager entityManager;
    private Class<?> entityType;
    private String columnName;

    public IdExistsValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(IdExists constraintAnnotation) {
        this.entityType = constraintAnnotation.entityType();
        this.columnName = constraintAnnotation.columnName();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        } else if (value <= 0) {
            return false;
        }
        String query = "SELECT COUNT(*) FROM " + entityType.getSimpleName() + " e WHERE e." + columnName + " = :value";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("value", value)
                .getSingleResult();
        return count == 1;
    }
}

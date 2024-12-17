package com.example.hospital_app_server.validation;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class IdsExistValidator implements ConstraintValidator<IdsExist, Set<Integer>> {
    private final EntityManager entityManager;
    private Class<?> entityType;
    private String columnName;

    public IdsExistValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(IdsExist constraintAnnotation) {
        this.entityType = constraintAnnotation.entityType();
        this.columnName = constraintAnnotation.columnName();
    }

    @Override
    public boolean isValid(Set<Integer> values, ConstraintValidatorContext constraintValidatorContext) {
        if (values == null) {
            return false;
        }
        String query = "SELECT COUNT(*) FROM " + entityType.getSimpleName() + " e WHERE e." + columnName + " IN (:values)";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("values", values)
                .getSingleResult();
        return count == values.size();
    }


}

package com.example.springautomapping.utils;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public interface ValidationUtil {
    <E> Set<ConstraintViolation<E>> getViolation (E entity);
}

package com.example.xml.util;

import jakarta.validation.ConstraintViolation;
import org.springframework.stereotype.Component;

import java.util.Set;


public interface ValidatorUtil {

   <E> boolean isValid(E entity);

   <E> Set<ConstraintViolation<E>> violation (E entity);
}

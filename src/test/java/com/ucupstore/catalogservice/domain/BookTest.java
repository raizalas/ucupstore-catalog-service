package com.ucupstore.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void whenAllFieldsAreValidSuccess() {
        var book = new Book("1111111111", "Title", "Author", 1.1);
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void whenIsbnIsInvalid() {
        var book = new Book("1a", "Title", "Author", 1.1);
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
        assertThat(constraintViolations).hasSize(1);
        assertThat(constraintViolations.iterator().next().getMessage())
                .isEqualTo("Must be a valid ISBN-10 or ISBN-13 format");
    }
}
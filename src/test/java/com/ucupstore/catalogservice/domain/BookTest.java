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
        Book book = Book.builder().isbn("1111111111").title("Title").author("Author").price(1.1).build();
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void whenIsbnIsInvalid() {
        Book book = Book.builder().isbn("1a").title("Title").author("Author").price(1.1).build();
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
        assertThat(constraintViolations).hasSize(1);
        assertThat(constraintViolations.iterator().next().getMessage())
                .isEqualTo("Must be a valid ISBN-10 or ISBN-13 format");
    }
}
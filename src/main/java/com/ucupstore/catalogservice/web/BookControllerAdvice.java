package com.ucupstore.catalogservice.web;

import com.ucupstore.catalogservice.domain.BookAlreadyExistsException;
import com.ucupstore.catalogservice.domain.BookNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BookControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(BookControllerAdvice.class);

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> bookNotFoundAdvice(BookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<?> bookAlreadyExistsAdvice(BookAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidAdvice(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
                    String errorField = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(errorField, errorMessage);
                }
        );
        logger.info(errors.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}

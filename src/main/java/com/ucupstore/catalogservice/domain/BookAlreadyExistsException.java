package com.ucupstore.catalogservice.domain;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String isbn) {
        super("The book with the isbn " + isbn + " already exists");
    }
}

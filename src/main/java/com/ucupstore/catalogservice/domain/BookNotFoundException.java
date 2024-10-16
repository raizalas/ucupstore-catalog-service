package com.ucupstore.catalogservice.domain;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn) {
        super("The book with the isbn = " + isbn + " was not found.");
    }
}

package com.ucupstore.catalogservice.web;

import com.ucupstore.catalogservice.domain.Book;
import com.ucupstore.catalogservice.domain.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Book>> getBooks() {
        Iterable<Book> books = bookService.viewBookList();
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable String isbn) {
        Book book = bookService.viewBookDetails(isbn);
        return ResponseEntity.ok().body(book);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book newBook = bookService.addBookToCatalog(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Book> updateBook(
            @PathVariable String isbn,
            @Valid @RequestBody Book book) {
        Book editedBookDetails = bookService.editBookDetails(isbn, book);
        return ResponseEntity.ok().body(editedBookDetails);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        bookService.removeBookFromCatalog(isbn);
        return ResponseEntity.noContent().build();
    }

}

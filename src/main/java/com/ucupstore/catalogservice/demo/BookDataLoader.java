package com.ucupstore.catalogservice.demo;

import com.ucupstore.catalogservice.domain.Book;
import com.ucupstore.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();
        Book book1 = Book.builder()
                .author("Lyra Silverstar")
                .isbn("1234567891")
                .title("Northern Lights")
                .price(9.90)
                .build();
        Book book2 = Book.builder()
                .author("Iorek Polarson")
                .isbn("1234567892")
                .title("Polar Journey")
                .price(11.90)
                .build();
        bookRepository.saveAll(List.of(book1,book2));
    }
}

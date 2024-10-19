package com.ucupstore.catalogservice.domain;

import com.ucupstore.catalogservice.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@ActiveProfiles("integration")
public class BookJpaTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TestEntityManager entityManager;
    @Test
    void findBookByIsbnWhenExisting() {
        var bookIsbn = "1234561237";
        Book book = Book.builder()
                        .title("Title")
                .isbn(bookIsbn)
                .author("Author")
                .price(12.90).build();

        entityManager.persist(book);
        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);
        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().getIsbn()).isEqualTo(book.getIsbn());
    }
}

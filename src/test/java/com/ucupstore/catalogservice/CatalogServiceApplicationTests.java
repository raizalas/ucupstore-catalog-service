package com.ucupstore.catalogservice;

import com.ucupstore.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("integration")
class CatalogServiceApplicationTests {

    @Autowired
    private WebTestClient webClient;

    @Test
    void contextLoads() {
    }

    @Test
    void whenPostRequestBookCreated() {
        Book book = Book.builder()
                .isbn("1111111111")
                .title("Northern Lights")
                .author("Ahmad Supardi")
                .price(9.90).build();

        webClient.post()
                .uri("/books")
                .bodyValue(book)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(actualBook -> {
                    assertThat(actualBook).isNotNull();
                    assertThat(actualBook.getIsbn()).isEqualTo(book.getIsbn());
                });
    }

}

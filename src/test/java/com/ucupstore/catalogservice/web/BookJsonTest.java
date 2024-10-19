package com.ucupstore.catalogservice.web;

import com.ucupstore.catalogservice.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTest {

    @Autowired
    private JacksonTester<Book> bookJacksonTester;

    @Test
    @DisplayName("Book Serialize test")
    public void toJson() throws Exception {
        Book book = Book.builder()
                .isbn("1111111111")
                .title("Northern Lights")
                .author("Ahmad Supardi")
                .price(9.90).build();
        JsonContent<Book> jsonContent = bookJacksonTester.write(book);

        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.getIsbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.getTitle());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.getAuthor());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.getPrice());
    }

    @Test
    @DisplayName("Json to Book object test")
    public void fromJson() throws Exception {
        var content = """
                {
                  "isbn": "1111111111",
                  "title": "Northern Lights",
                  "author": "Ahmad Supardi",
                  "price": 9.9
                }
                """;
        assertThat(bookJacksonTester.parse(content)).usingRecursiveComparison().isEqualTo(
                Book.builder()
                        .isbn("1111111111")
                        .title("Northern Lights")
                        .author("Ahmad Supardi")
                        .price(9.90).build()
        );
    }
}

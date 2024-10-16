package com.ucupstore.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Book(
        @NotBlank(message = "ISBN must not be empty")
        @Pattern(
                regexp = "^([0-9]{10}|[0-9]{13})$",
                message = "Must be a valid ISBN-10 or ISBN-13 format"
        )
        String isbn,

        @NotBlank(message = "Title must not be empty")
        String title,

        @NotBlank(message = "Author must not be empty")
        String author,

        @NotNull(message = "Price must not be empty")
        @Positive(message = "Price must be larger than zero")
        Double price
) {
}

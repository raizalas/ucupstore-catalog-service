package com.ucupstore.catalogservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);

    @Transactional
    void deleteByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

}
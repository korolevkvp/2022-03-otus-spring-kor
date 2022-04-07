package ru.otus.spring.repository;

import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa {
    Book save(Book book);
    Optional<Book> findById(long id);

    List<Book> findAll();
    List<Book> findByTitle(String name);

    void updateTitleById(long id, String name);
    void deleteById(long id);
}

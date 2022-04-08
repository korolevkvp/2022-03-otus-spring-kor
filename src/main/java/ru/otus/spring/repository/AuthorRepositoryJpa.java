package ru.otus.spring.repository;

import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.AuthorNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AuthorRepositoryJpa {

    Author save(Author author);
    List<Author> findAll();
    Optional<Author> findById(long id);
    List<Author> findByName(String name);
    void deleteById(Long id);

}

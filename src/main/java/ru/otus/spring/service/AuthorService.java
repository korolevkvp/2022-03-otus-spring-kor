package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.AuthorNotFoundException;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author findById(Long id) throws AuthorNotFoundException;

    List<Author> findByName(String name);

    void deleteById(Long id) throws AuthorNotFoundException;

    Author create(Author author);
}

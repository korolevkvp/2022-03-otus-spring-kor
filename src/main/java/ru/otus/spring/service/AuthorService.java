package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.AuthorNotFoundException;

import java.util.List;

public interface AuthorService {

    List<Author> findAllAuthors();

    Author updateAuthorById(Author author);

    Author findAuthorById(Long id) throws AuthorNotFoundException;

    void deleteAuthorById(Long id) throws AuthorNotFoundException;

    Author createAuthor(Author author);
}

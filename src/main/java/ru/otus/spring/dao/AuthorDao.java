package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.AuthorNotFoundException;
import ru.otus.spring.exception.GenreNotFoundException;

import java.util.List;

public interface AuthorDao {

    int count();

    void save(Author author);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id) throws AuthorNotFoundException;

}

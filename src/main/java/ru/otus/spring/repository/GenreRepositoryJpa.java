package ru.otus.spring.repository;

import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.BookNotFoundException;
import ru.otus.spring.exception.GenreNotFoundException;

import java.util.List;
import java.util.Optional;

public interface GenreRepositoryJpa {

    Genre save(Genre genre);
    Optional<Genre> findById(long id);

    List<Genre> findAll();
    List<Genre> findByName(String name);
    void deleteById(long id) throws GenreNotFoundException;

}

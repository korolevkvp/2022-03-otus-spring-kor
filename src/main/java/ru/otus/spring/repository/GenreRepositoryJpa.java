package ru.otus.spring.repository;

import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepositoryJpa {

    Genre save(Genre genre);
    Optional<Genre> findById(long id);

    List<Genre> findAll();
    List<Genre> findByName(String name);
}

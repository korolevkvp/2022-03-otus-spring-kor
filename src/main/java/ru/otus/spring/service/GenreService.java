package ru.otus.spring.service;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreNotFoundException;

import java.util.List;

public interface GenreService {

    List<Genre> findAll();

    Genre updateById(Genre genre) throws GenreNotFoundException;

    Genre findById(Long id) throws GenreNotFoundException;

    void deleteById(Long id) throws GenreNotFoundException;

    List<Genre> findByName(String name);

    Genre create(Genre genre);
}

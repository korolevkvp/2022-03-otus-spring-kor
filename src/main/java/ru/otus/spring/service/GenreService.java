package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreNotFoundException;

import java.util.List;

public interface GenreService {

    List<Genre> findAllGenres();

    Genre updateGenreById(Genre genre);

    Genre findGenreById(Long id) throws GenreNotFoundException;

    void deleteGenreById(Long id) throws GenreNotFoundException;

    Genre createGenre(Genre genre);
}

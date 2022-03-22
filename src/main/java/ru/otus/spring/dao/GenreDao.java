package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {

    int count();

    void save(Genre author);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);
}

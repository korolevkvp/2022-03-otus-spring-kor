package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreRepositoryJpa extends JpaRepository<Genre, Long> {
    List<Genre> findAllByName(String name);

}

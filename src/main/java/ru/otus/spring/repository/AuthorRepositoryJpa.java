package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorRepositoryJpa extends JpaRepository<Author, Long> {

    List<Author> findAllByName(String name);
}

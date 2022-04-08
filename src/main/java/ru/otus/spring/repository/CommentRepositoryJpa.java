package ru.otus.spring.repository;

import ru.otus.spring.domain.Comment;
import ru.otus.spring.exception.CommentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryJpa {
    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    void deleteById(Long id);

    List<Comment> findAll();

    List<Comment> findByAuthor(String author);

    List<Comment> saveAll(List<Comment> comments);
}

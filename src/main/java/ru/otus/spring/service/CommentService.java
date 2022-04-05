package ru.otus.spring.service;

import ru.otus.spring.domain.Comment;
import ru.otus.spring.exception.CommentNotFoundException;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    void deleteById(Long id) throws CommentNotFoundException;

    Comment findById(Long id) throws CommentNotFoundException;

    List<Comment> findByAuthor(String author);

    Comment create(Comment comment);
}

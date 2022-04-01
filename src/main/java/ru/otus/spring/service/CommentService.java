package ru.otus.spring.service;

import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.exception.CommentNotFoundException;

import java.util.List;

public interface CommentService {

    List<Comment> findAllComments();

    Comment updateCommentById(Comment comment);

    Comment findCommentById(Long id) throws CommentNotFoundException;

    void deleteCommentById(Long id) throws CommentNotFoundException;

    Comment createComment(Comment comment);
}

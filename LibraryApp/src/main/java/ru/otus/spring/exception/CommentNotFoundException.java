package ru.otus.spring.exception;

public class CommentNotFoundException extends Exception {
    public CommentNotFoundException(long id) {
        super("Can't find comment with id = " + id);
    }
}

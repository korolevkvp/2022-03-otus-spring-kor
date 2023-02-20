package ru.otus.spring.exception;

public class AuthorNotFoundException extends Exception {
    public AuthorNotFoundException(long id) {
        super("Can't find author with id = " + id);
    }
}

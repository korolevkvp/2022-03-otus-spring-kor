package ru.otus.spring.exception;

public class AuthorNotFoundException extends Throwable {
    public AuthorNotFoundException(long id) {
        super("Can't find author with id = " + id);
    }
}

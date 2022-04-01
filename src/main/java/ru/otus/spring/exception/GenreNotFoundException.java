package ru.otus.spring.exception;

public class GenreNotFoundException extends Exception {
    public GenreNotFoundException(long id) {
        super("Can't find genre with id = " + id);
    }
}

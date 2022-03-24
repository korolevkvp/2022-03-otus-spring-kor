package ru.otus.spring.exception;

public class GenreNotFoundException extends Throwable {
    public GenreNotFoundException(long id) {
        super("Can't find genre with id = " + id);
    }
}

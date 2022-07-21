package ru.otus.spring.exception;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(long id) {
        super("Can't find book with id = " + id);
    }
}

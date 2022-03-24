package ru.otus.spring.exception;

public class BookNotFoundException extends Throwable {
    public BookNotFoundException(long id) {
        super(String.valueOf(id));
    }
}

package ru.otus.spring.service;

import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookNotFoundException;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book updateById(Book book);

    Book findById(Long id) throws BookNotFoundException;

    void deleteById(Long id) throws BookNotFoundException;

    Book create(Book book);
}
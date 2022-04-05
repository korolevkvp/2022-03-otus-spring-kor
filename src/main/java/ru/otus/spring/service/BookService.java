package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book updateById(Book book);

    Book findById(Long id);

    void deleteById(Long id);

    Book create(Book book);
}

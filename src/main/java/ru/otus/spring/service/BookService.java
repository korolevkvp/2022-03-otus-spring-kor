package ru.otus.spring.service;

import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookNotFoundException;

import java.util.List;

public interface BookService {

    List<Book> findAllBooks();

    Book updateBookById(Book book);

    Book findBookById(Long id) throws BookNotFoundException;

    void deleteBookById(Long id) throws BookNotFoundException;

    Book createBook(Book book);
}

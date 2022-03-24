package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookNotFoundException;

import java.util.List;

public interface BookDao {

    int count();

    Long save(Book author);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id) throws BookNotFoundException;

}

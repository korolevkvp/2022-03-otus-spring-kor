package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {

    int count();

    void save(Book author);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);

}

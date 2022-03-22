package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookCreate;
import ru.otus.spring.domain.Genre;

import java.util.List;

public interface LibraryService {

    List<Book> findAllBooks();
    List<Genre> findAllGenres();
    List<Author> findAllAuthors();

    Book updateBookById(Long id, BookCreate bookCreate);

    void deleteBookById(Long id);

    void createBook(BookCreate bookCreate);
}

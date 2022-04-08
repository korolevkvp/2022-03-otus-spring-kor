package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookNotFoundException;
import ru.otus.spring.repository.AuthorRepositoryJpa;
import ru.otus.spring.repository.BookRepositoryJpa;
import ru.otus.spring.repository.CommentRepositoryJpa;
import ru.otus.spring.repository.GenreRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookConsoleService implements BookService {

    private final BookRepositoryJpa bookRepositoryJpa;
    private final AuthorRepositoryJpa authorRepositoryJpa;
    private final GenreRepositoryJpa genreRepositoryJpa;
    private final CommentRepositoryJpa commentRepositoryJpa;


    @Override
    public List<Book> findAll() {
        return bookRepositoryJpa.findAll();
    }

    @Override
    @Transactional
    public Book updateById(Long id, Book book) {
        bookRepositoryJpa.deleteById(id);
        book.setId(id);
        return saveBookWithInnerFields(book);
    }

    @Override
    public Book findById(Long id) throws BookNotFoundException {
        Optional<Book> b = bookRepositoryJpa.findById(id);
        if (b.isPresent()) {
            return b.get();
        } else {
            throw new BookNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepositoryJpa.deleteById(id);
    }

    @Override
    @Transactional
    public Book create(Book book) {
        return saveBookWithInnerFields(book);
    }

    private Book saveBookWithInnerFields(Book book) {
        if (book.getAuthor() != null) authorRepositoryJpa.save(book.getAuthor());
        if (book.getGenre() != null) genreRepositoryJpa.save(book.getGenre());
        if (book.getComments() != null) commentRepositoryJpa.saveAll(book.getComments());
        book = bookRepositoryJpa.save(book);
        return book;
    }
}

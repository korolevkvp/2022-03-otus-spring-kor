package ru.otus.spring.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookNotFoundException;
import ru.otus.spring.repository.AuthorRepositoryJpa;
import ru.otus.spring.repository.BookRepositoryJpa;
import ru.otus.spring.repository.CommentRepositoryJpa;
import ru.otus.spring.repository.GenreRepositoryJpa;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookConsoleService implements BookService {

    private final BookRepositoryJpa bookRepositoryJpa;
    private final AuthorRepositoryJpa authorRepositoryJpa;
    private final GenreRepositoryJpa genreRepositoryJpa;
    private final CommentRepositoryJpa commentRepositoryJpa;

    private final static String HYSTRIX_MESSAGE = "Извините, сейчас мы не можем дать вам ответ";

    @HystrixCommand(commandKey = "findAll", fallbackMethod = "fallbackFindAll")
    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepositoryJpa.findAll();
    }

    @HystrixCommand(commandKey = "updateById", fallbackMethod = "fallbackFindOne")
    @Override
    @Transactional
    public Book updateById(Long id, Book book) {
        book.setId(id);
        return saveBookWithInnerFields(book);
    }

    @HystrixCommand(commandKey = "findById", fallbackMethod = "fallbackFindOne")
    @Override
    @Transactional(readOnly = true)
    public Book findById(Long id) throws BookNotFoundException {
        Optional<Book> b = bookRepositoryJpa.findById(id);
        if (b.isPresent()) {
            return b.get();
        } else {
            throw new BookNotFoundException(id);
        }
    }

    @HystrixCommand(commandKey = "deleteById", fallbackMethod = "fallbackFindOne")
    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepositoryJpa.deleteById(id);
    }

    @HystrixCommand(commandKey = "create", fallbackMethod = "fallbackFindOne")
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

    private Book fallbackBook() {
        Book book = new Book();
        book.setTitle(HYSTRIX_MESSAGE);
        return book;
    }
    public List<Book> fallbackFindAll() {
        return Collections.singletonList(fallbackBook());
    }
    public Book fallbackFindOne() {
        return fallbackBook();
    }
}

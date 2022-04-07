package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookNotFoundException;
import ru.otus.spring.repository.BookRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookConsoleService implements BookService {

    private final BookRepositoryJpa bookRepositoryJpa;


    @Override
    public List<Book> findAll() {
        return bookRepositoryJpa.findAll();
    }

    @Override
    @Transactional
    public Book updateById(Book book) throws BookNotFoundException {
        bookRepositoryJpa.deleteById(book.getId());
        book = bookRepositoryJpa.save(book);
        return book;
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
    public void deleteById(Long id) throws BookNotFoundException {
        bookRepositoryJpa.deleteById(id);
    }

    @Override
    @Transactional
    public Book create(Book book) {
        book = bookRepositoryJpa.save(book);
        return book;
    }
}

package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public List<Book> findAllBooks() {
        return bookRepositoryJpa.findAll();
    }

    @Override
    public Book updateBookById(Book book) {
        if (bookRepositoryJpa.findById(book.getId()).isPresent()) {
            try {
                bookRepositoryJpa.deleteById(book.getId());
            } catch (BookNotFoundException ignored) {
            }
        }
        book = bookRepositoryJpa.save(book);
        return book;
    }

    @Override
    public Book findBookById(Long id) throws BookNotFoundException {
        Optional<Book> b = bookRepositoryJpa.findById(id);
        if (b.isPresent()) {
            return b.get();
        } else {
            throw new BookNotFoundException(id);
        }
    }

    @Override
    public void deleteBookById(Long id) throws BookNotFoundException {
        bookRepositoryJpa.deleteById(id);
    }

    @Override
    public Book createBook(Book book) {
        book = bookRepositoryJpa.save(book);
        return book;
    }
}

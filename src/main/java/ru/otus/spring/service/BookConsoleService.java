package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookConsoleService implements BookService {

    private final BookDao bookDao;

    @Override
    public List<Book> findAll() {
        return bookDao.getAll();
    }

    @Override
    public Book updateById(Book book) {
        bookDao.update(book);
        return book;
    }

    @Override
    public Book findById(Long id) {
        return bookDao.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        try {
            bookDao.deleteById(id);
        } catch (BookNotFoundException e) {
            System.out.println(e);
        }
    }

    @Override
    public Book create(Book book) {
        Long id = bookDao.save(book);
        book.setId(id);
        return book;
    }
}

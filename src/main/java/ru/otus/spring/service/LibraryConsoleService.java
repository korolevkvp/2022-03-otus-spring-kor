package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookCreate;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.mapper.BookMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryConsoleService implements LibraryService {

    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final BookDao bookDao;

    private final BookMapper bookMapper;

    @Override
    public List<Book> findAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreDao.getAll();
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorDao.getAll();
    }

    @Override
    public Book updateBookById(Long id, BookCreate bookCreate) {
        bookDao.getById(id);
        bookDao.deleteById(id);
        Book book = bookMapper.bookCreateToBook(bookCreate);
        book.setId(id);
        bookDao.save(book);
        return book;
    }

    @Override
    public Book findBookById(Long id) {
        return bookDao.getById(id);
    }

    @Override
    public void deleteBookById(Long id) {
        bookDao.deleteById(id);
    }

    @Override
    public Book createBook(BookCreate bookCreate) {
        Book book = bookMapper.bookCreateToBook(bookCreate);
        Long id = bookDao.save(book);
        book.setId(id);
        return book;
    }
}

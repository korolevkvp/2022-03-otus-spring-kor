package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.GenreService;

import java.sql.SQLException;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class LibraryApplicationCommands {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @ShellMethod(value = "Find all books", key = {"b", "books"})
    public List<Book> findAllBooks() {
        return bookService.findAll();
    }

    @ShellMethod(value = "Find all genres", key = {"g", "genres"})
    public List<Genre> findAllGenres() {
        return genreService.findAll();
    }

    @ShellMethod(value = "Find all authors", key = {"a", "authors"})
    public List<Author> findAllAuthors() {
        return authorService.findAll();
    }

    @ShellMethod(value = "Find book by id", key = {"fb", "find_book"})
    public Book findBookById(Long id) throws Exception {
        try {
            return bookService.findById(id);
        } catch (Exception e) {
            throw new SQLException("There is no book with id = " + id);
        }
    }

    @ShellMethod(value = "Update book by id (example: 3,Buratino,8,4,1)", key = {"ub", "update_book"})
    public Book updateBookById(Book book) throws Exception {
        try {
            return bookService.updateById(book);
        } catch (Exception e) {
            throw new SQLException("There is no book with id = " + book.getId());
        }
    }

    @ShellMethod(value = "Delete book by id", key = {"db", "delete_book"})
    public void deleteBookById(Long id) throws Exception {
        try {
            bookService.deleteById(id);
            System.out.println("Book with id = " + id +" successfully deleted");
        } catch (Exception e) {
            throw new SQLException("There is no book with id = " + id);
        }
    }

    @ShellMethod(value = "Create book (example: Buratino,8,4,1)", key = {"cb", "add_book"})
    public Book createBook(Book book) {
        return bookService.create(book);
    }
}

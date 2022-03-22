package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookCreate;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.LibraryService;

import java.sql.SQLException;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class LibraryApplicationCommands {

    private final LibraryService libraryService;

    @ShellMethod(value = "Find all books", key = {"b", "books"})
    public List<Book> findAllBooks() {
        return libraryService.findAllBooks();
    }

    @ShellMethod(value = "Find all genres", key = {"g", "genres"})
    public List<Genre> findAllGenres() {
        return libraryService.findAllGenres();
    }

    @ShellMethod(value = "Find all authors", key = {"a", "authors"})
    public List<Author> findAllAuthors() {
        return libraryService.findAllAuthors();
    }

    @ShellMethod(value = "Find book by id", key = {"fb", "find_book"})
    public Book findBookById(Long id) throws Exception {
        try {
            return libraryService.findBookById(id);
        } catch (Exception e) {
            throw new SQLException("There is no book with id = " + id);
        }
    }

    @ShellMethod(value = "Update book by id (example: 3 Buratino,8,4,1)", key = {"ub", "update_book"})
    public Book updateBookById(Long id, BookCreate bookCreate) throws Exception {
        try {
            return libraryService.updateBookById(id, bookCreate);
        } catch (Exception e) {
            throw new SQLException("There is no book with id = " + id);
        }
    }

    @ShellMethod(value = "Delete book by id", key = {"db", "delete_book"})
    public void deleteBookById(Long id) throws Exception {
        try {
            libraryService.deleteBookById(id);
            System.out.println("Book with id = " + id +" successfully deleted");
        } catch (Exception e) {
            throw new SQLException("There is no book with id = " + id);
        }
    }

    @ShellMethod(value = "Create book (example: Buratino,8,4,1)", key = {"cb", "add_book"})
    public Book createBook(BookCreate bookCreate) {
        return libraryService.createBook(bookCreate);
    }
}

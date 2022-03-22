package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookCreate;
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

    @ShellMethod(value = "Update book by id", key = {"ub", "update_book"})
    public Book updateBookById(Long id, BookCreate bookCreate) throws Exception {
        try {
            return libraryService.updateBookById(id, bookCreate);
        } catch (Exception e) {
            throw new SQLException("There is no book with id = " + id);
        }
    }
}

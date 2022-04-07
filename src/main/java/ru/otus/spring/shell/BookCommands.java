package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookNotFoundException;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.ReaderService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private final BookService bookService;
    private final ReaderService readerService;

    @ShellMethod(value = "Find all books", key = {"b", "books"})
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @ShellMethod(value = "Find book by id", key = {"fb", "find_book"})
    public Book findById(Long id) {
        try {
            return bookService.findById(id);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @ShellMethod(value = "Update book by id", key = {"ub", "update_book"})
    public Book updateById(Long id) {
        return bookService.updateById(id, readerService.readBook());
    }

    @ShellMethod(value = "Delete book by id", key = {"db", "delete_book"})
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

    @ShellMethod(value = "Create book", key = {"cb", "create_book"})
    public Book create() {
        return bookService.create(readerService.readBook());
    }
}

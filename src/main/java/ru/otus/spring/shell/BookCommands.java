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
    public List<Book> findAllBooks() {
        return bookService.findAll();
    }

    @ShellMethod(value = "Find book by id", key = {"fb", "find_book"})
    public Book findBookById(Long id) {
        try {
            return bookService.findById(id);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @ShellMethod(value = "Update book by id", key = {"ub", "update_book"})
    public Book updateBookById() {
        try {
            return bookService.updateById(readerService.readBook());
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @ShellMethod(value = "Delete book by id", key = {"db", "delete_book"})
    public void deleteBookById(Long id) {
        try {
            bookService.deleteById(id);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod(value = "Create book", key = {"cb", "create_book"})
    public Book createBook() {
        return bookService.create(readerService.readBook());
    }
}

package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookNotFoundException;
import ru.otus.spring.service.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class LibraryBookCommands {

    private final BookService bookService;

    @ShellMethod(value = "Find all books", key = {"b", "books"})
    public List<Book> findAllBooks() {
        return bookService.findAll();
    }

//    @ShellMethod(value = "Find all genres", key = {"g", "genres"})
//    public List<Genre> findAllGenres() {
//        return bookService.findAllGenres();
//    }

//    @ShellMethod(value = "Find all authors", key = {"a", "authors"})
//    public List<Author> findAllAuthors() {
//        return bookService.findAllAuthors();
//    }

    @ShellMethod(value = "Find book by id", key = {"fb", "find_book"})
    public Book findBookById(Long id) throws BookNotFoundException {
        return bookService.findById(id);
    }

    @ShellMethod(value = "Update book by id (example: 3,Buratino,8,4,1)", key = {"ub", "update_book"})
    public Book updateBookById(Book book) {
        return bookService.updateById(book);
    }

    @ShellMethod(value = "Delete book by id", key = {"db", "delete_book"})
    public void deleteBookById(Long id) throws BookNotFoundException {
        bookService.deleteById(id);
    }

    @ShellMethod(value = "Create book (example: Buratino,8,4,1)", key = {"cb", "add_book"})
    public Book createBook(Book book) {
        return bookService.create(book);
    }
}

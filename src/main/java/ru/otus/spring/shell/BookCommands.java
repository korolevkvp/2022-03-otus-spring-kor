package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.ReaderService;
import ru.otus.spring.service.printer.BookPrinterService;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private final BookPrinterService bookPrinterService;
    private final ReaderService readerService;

    @ShellMethod(value = "Find all books", key = {"b", "books"})
    public void findAll() {
        bookPrinterService.findAll();
    }

    @ShellMethod(value = "Find book by id", key = {"fb", "find_book"})
    public void findById(Long id) {
        bookPrinterService.findById(id);
    }

    @ShellMethod(value = "Update book by id", key = {"ub", "update_book"})
    public void updateById(Long id) {
        bookPrinterService.updateById(id, readerService.readBook());
    }

    @ShellMethod(value = "Delete book by id", key = {"db", "delete_book"})
    public void deleteById(Long id) {
        bookPrinterService.deleteById(id);
    }

    @ShellMethod(value = "Create book", key = {"cb", "create_book"})
    public void create() {
        bookPrinterService.create(readerService.readBook());
    }
}

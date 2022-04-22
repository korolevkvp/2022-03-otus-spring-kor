package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.ReaderService;
import ru.otus.spring.service.printer.AuthorPrinterService;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {

    private final AuthorPrinterService authorPrinterService;
    private final ReaderService readerService;

    @ShellMethod(value = "Find all authors", key = {"a", "authors"})
    public void findAll() {
        authorPrinterService.findAll();
    }

    @ShellMethod(value = "Find author by id", key = {"fa", "find_author"})
    public void findById(Long id) {
        authorPrinterService.findById(id);
    }


    @ShellMethod(value = "Delete author by id", key = {"da", "delete_author"})
    public void deleteById(Long id) {
        authorPrinterService.deleteById(id);
    }

    @ShellMethod(value = "Create author", key = {"ca", "create_author"})
    public void create() {
        authorPrinterService.create(readerService.readAuthor());
    }
}

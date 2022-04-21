package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.ReaderService;
import ru.otus.spring.service.printer.GenrePrinterService;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {

    private final GenrePrinterService genrePrinterService;
    private final ReaderService readerService;

    @ShellMethod(value = "Find all genres", key = {"g", "genres"})
    public void findAll() {
        genrePrinterService.findAll();
    }

    @ShellMethod(value = "Find genre by id", key = {"fg", "find_genre"})
    public void findById(Long id) {
        genrePrinterService.findById(id);
    }

    @ShellMethod(value = "Delete genre by id", key = {"dg", "delete_genre"})
    public void deleteById(Long id) {
        genrePrinterService.deleteById(id);
    }

    @ShellMethod(value = "Create genre", key = {"cg", "create_genre"})
    public void create() {
        genrePrinterService.create(readerService.readGenre());
    }
}

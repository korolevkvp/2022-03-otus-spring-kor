package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.ReaderService;
import ru.otus.spring.service.printer.CommentPrinterService;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {

    private final CommentPrinterService commentPrinterService;
    private final ReaderService readerService;

    @ShellMethod(value = "Find all comments", key = {"c", "comments"})
    public void findAll() {
        commentPrinterService.findAll();
    }

    @ShellMethod(value = "Find comment by id", key = {"fc", "find_comment"})
    public void findById(Long id) {
        commentPrinterService.findById(id);
    }

    @ShellMethod(value = "Delete comment by id", key = {"dc", "delete_comment"})
    public void deleteById(Long id) {
        commentPrinterService.deleteById(id);
    }

    @ShellMethod(value = "Create comment", key = {"cc", "create_comment"})
    public void create() {
        commentPrinterService.create(readerService.readComment());
    }
}

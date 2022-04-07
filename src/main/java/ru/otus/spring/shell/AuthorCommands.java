package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.AuthorNotFoundException;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.ReaderService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {

    private final AuthorService authorService;
    private final ReaderService readerService;

    @ShellMethod(value = "Find all authors", key = {"a", "authors"})
    public List<Author> findAllAuthors() {
        return authorService.findAll();
    }

    @ShellMethod(value = "Find author by id", key = {"fa", "find_author"})
    public Author findAuthorById(Long id) {
        try {
            return authorService.findById(id);
        } catch (AuthorNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    @ShellMethod(value = "Delete author by id", key = {"da", "delete_author"})
    public void deleteAuthorById(Long id) {
        try {
            authorService.deleteById(id);
        } catch (AuthorNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod(value = "Create author", key = {"ca", "create_author"})
    public Author createAuthor() {
        return authorService.create(readerService.readAuthor());
    }
}

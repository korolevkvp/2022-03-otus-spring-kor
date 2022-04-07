package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreNotFoundException;
import ru.otus.spring.service.GenreService;
import ru.otus.spring.service.ReaderService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {

    private final GenreService genreService;
    private final ReaderService readerService;

    @ShellMethod(value = "Find all genres", key = {"g", "genres"})
    public List<Genre> findAll() {
        return genreService.findAll();
    }

    @ShellMethod(value = "Find genre by id", key = {"fg", "find_genre"})
    public Genre findById(Long id) {
        try {
            return genreService.findById(id);
        } catch (GenreNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @ShellMethod(value = "Delete genre by id", key = {"dg", "delete_genre"})
    public void deleteById(Long id) {
        genreService.deleteById(id);
    }

    @ShellMethod(value = "Create genre", key = {"cg", "create_genre"})
    public Genre create() {
        return genreService.create(readerService.readGenre());
    }
}

package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreNotFoundException;
import ru.otus.spring.service.GenreService;
import ru.otus.spring.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping("genre")
@RequiredArgsConstructor
public class GenreCommands {

    private final GenreService genreService;
    private final ReaderService readerService;

    @GetMapping
    public List<Genre> findAll() {
        return genreService.findAll();
    }

    @GetMapping("{id}")
    public Genre findById(@PathVariable("id") Long id) throws GenreNotFoundException {
        return genreService.findById(id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        genreService.deleteById(id);
    }

    @PostMapping
    public Genre create() {
        return genreService.create(readerService.readGenre());
    }
}

package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.controller.dto.GenreDto;
import ru.otus.spring.exception.GenreNotFoundException;
import ru.otus.spring.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("genre")
@RequiredArgsConstructor
public class GenreCommands {

    private final GenreService genreService;

    @GetMapping
    public List<GenreDto> findAll() {
        return genreService.findAll().stream()
                .map(GenreDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public GenreDto findById(@PathVariable("id") Long id) throws GenreNotFoundException {
        return GenreDto.toDto(genreService.findById(id));
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        genreService.deleteById(id);
    }

    @PostMapping
    public GenreDto create(@RequestBody GenreDto genre) {
        return GenreDto.toDto(genreService.create(GenreDto.toDomainObject(genre)));
    }
}

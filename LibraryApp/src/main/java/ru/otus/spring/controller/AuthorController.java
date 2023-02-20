package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.controller.dto.AuthorDto;
import ru.otus.spring.exception.AuthorNotFoundException;
import ru.otus.spring.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<AuthorDto> findAll() {
        return authorService.findAll().stream().map(AuthorDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public AuthorDto findById(@PathVariable("id") Long id) throws AuthorNotFoundException {
        return AuthorDto.toDto(authorService.findById(id));
    }


    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        authorService.deleteById(id);
    }

    @PostMapping
    public AuthorDto create(@RequestBody AuthorDto author) {
        return AuthorDto.toDto(authorService.create(AuthorDto.toDomainObject(author)));
    }

}

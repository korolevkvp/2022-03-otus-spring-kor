package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.controller.dto.BookDto;
import ru.otus.spring.exception.BookNotFoundException;
import ru.otus.spring.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookDto> findAll() {
        return bookService.findAll().stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public BookDto findById(@PathVariable("id") Long id) throws BookNotFoundException {
        return BookDto.toDto(bookService.findById(id));
    }

    @PutMapping("{id}")
    public BookDto updateById(@PathVariable("id") Long id, @RequestBody BookDto book) {
        return BookDto.toDto(bookService.updateById(id, BookDto.toDomainObject(book)));
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }

    @PostMapping
    public BookDto create(@RequestBody BookDto book) {
        return BookDto.toDto(bookService.create(BookDto.toDomainObject(book)));
    }
}

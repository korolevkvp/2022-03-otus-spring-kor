package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookNotFoundException;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ReaderService readerService;

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("{id}")
    public Book findById(@PathVariable("id") Long id) throws BookNotFoundException {
        return bookService.findById(id);
    }

    @PostMapping("{id}")
    public Book updateById(@PathVariable("id") Long id) {
        return bookService.updateById(id, readerService.readBook());
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }

    @PostMapping
    public Book create() {
        return bookService.create(readerService.readBook());
    }
}

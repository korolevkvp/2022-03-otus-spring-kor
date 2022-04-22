package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.service.ReaderService;
import ru.otus.spring.service.printer.AuthorPrinterService;

@RestController
@RequestMapping("author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorPrinterService authorPrinterService;
    private final ReaderService readerService;

    @GetMapping
    public void findAll() {
        authorPrinterService.findAll();
    }

    @GetMapping("{id}")
    public void findById(@PathVariable("id") Long id) {
        authorPrinterService.findById(id);
    }


    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        authorPrinterService.deleteById(id);
    }

    @PostMapping
    public void create() {
        authorPrinterService.create(readerService.readAuthor());
    }
}

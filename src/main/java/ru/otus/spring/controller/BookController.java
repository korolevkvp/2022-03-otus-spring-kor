package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.service.ReaderService;
import ru.otus.spring.service.printer.BookPrinterService;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    private final BookPrinterService bookPrinterService;
    private final ReaderService readerService;

    @GetMapping
    public void findAll() {
        bookPrinterService.findAll();
    }

    @GetMapping("{id}")
    public void findById(@PathVariable("id") Long id) {
        bookPrinterService.findById(id);
    }

    @PostMapping("{id}")
    public void updateById(@PathVariable("id") Long id) {
        bookPrinterService.updateById(id, readerService.readBook());
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        bookPrinterService.deleteById(id);
    }

    @PostMapping
    public void create() {
        bookPrinterService.create(readerService.readBook());
    }
}

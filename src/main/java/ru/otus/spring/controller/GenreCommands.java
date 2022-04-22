package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.service.ReaderService;
import ru.otus.spring.service.printer.GenrePrinterService;

@RestController
@RequestMapping("genre")
@RequiredArgsConstructor
public class GenreCommands {

    private final GenrePrinterService genrePrinterService;
    private final ReaderService readerService;

    @GetMapping
    public void findAll() {
        genrePrinterService.findAll();
    }

    @GetMapping("{id}")
    public void findById(@PathVariable("id") Long id) {
        genrePrinterService.findById(id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        genrePrinterService.deleteById(id);
    }

    @PostMapping
    public void create() {
        genrePrinterService.create(readerService.readGenre());
    }
}

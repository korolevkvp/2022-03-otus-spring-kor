package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.service.ReaderService;
import ru.otus.spring.service.printer.CommentPrinterService;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentPrinterService commentPrinterService;
    private final ReaderService readerService;

    @GetMapping
    public void findAll() {
        commentPrinterService.findAll();
    }

    @GetMapping("{id}")
    public void findById(@PathVariable("id") Long id) {
        commentPrinterService.findById(id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        commentPrinterService.deleteById(id);
    }

    @PostMapping
    public void create() {
        commentPrinterService.create(readerService.readComment());
    }
}

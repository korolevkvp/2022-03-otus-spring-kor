package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.exception.CommentNotFoundException;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final ReaderService readerService;

    @GetMapping
    public List<Comment> findAll() {
        return commentService.findAll();
    }

    @GetMapping("{id}")
    public Comment findById(@PathVariable("id") Long id) throws CommentNotFoundException {
        return commentService.findById(id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        commentService.deleteById(id);
    }

    @PostMapping
    public Comment create() {
        return commentService.create(readerService.readComment());
    }
}

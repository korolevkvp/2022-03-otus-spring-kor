package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.controller.dto.CommentDto;
import ru.otus.spring.exception.CommentNotFoundException;
import ru.otus.spring.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> findAll() {
        return commentService.findAll().stream()
                .map(CommentDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public CommentDto findById(@PathVariable("id") Long id) throws CommentNotFoundException {
        return CommentDto.toDto(commentService.findById(id));
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        commentService.deleteById(id);
    }

    @PostMapping
    public CommentDto create(@RequestBody CommentDto comment) {
        return CommentDto.toDto(commentService.create(CommentDto.toDomainObject(comment)));
    }
}

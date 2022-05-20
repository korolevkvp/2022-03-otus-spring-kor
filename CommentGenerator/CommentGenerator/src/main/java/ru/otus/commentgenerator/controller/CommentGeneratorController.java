package ru.otus.commentgenerator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.commentgenerator.domain.Comment;
import ru.otus.commentgenerator.service.CommentGeneratorService;

@RestController
@RequiredArgsConstructor
public class CommentGeneratorController {

    private final CommentGeneratorService service;

    @GetMapping("generate")
    public Comment generate() {
        return service.generate();
    }

}

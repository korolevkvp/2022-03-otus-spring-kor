package ru.otus.commentgenerator.service;

import org.springframework.stereotype.Service;
import ru.otus.commentgenerator.domain.Comment;

import java.util.Random;

@Service
public class CommentGeneratorServicePrimary implements CommentGeneratorService{
    @Override
    public Comment generate() {
        return Comment.builder()
                .author("Generator")
                .content(new Random().nextBoolean() ? "Bad" : "Good")
                .build();
    }
}

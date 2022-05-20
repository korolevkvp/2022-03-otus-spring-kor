package ru.otus.commentgenerator.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.commentgenerator.domain.Comment;

@Data
@AllArgsConstructor
public class CommentDto {

    private Long id;

    private String author;

    private String content;

    public static Comment toDomainObject(CommentDto dto) {
        return new Comment(dto.getId(), dto.getAuthor(), dto.getContent());
    }

    public static CommentDto toDto(Comment domain) {
        return new CommentDto(domain.getId(), domain.getAuthor(), domain.getContent());
    }
}

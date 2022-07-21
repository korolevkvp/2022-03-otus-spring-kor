package ru.otus.spring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.List;

@Data
@AllArgsConstructor
public class BookDto {

    private Long id;

    private String title;

    private Integer rating;

    private Author author;

    private Genre genre;

    private List<Comment> comments;

    public static Book toDomainObject(BookDto dto) {
        return new Book(dto.getId(), dto.getTitle(), dto.getRating(), dto.getAuthor(), dto.getGenre(), dto.getComments());
    }

    public static BookDto toDto(Book domain) {
        return new BookDto(domain.getId(), domain.getTitle(), domain.getRating(), domain.getAuthor(), domain.getGenre(), domain.getComments());
    }
}

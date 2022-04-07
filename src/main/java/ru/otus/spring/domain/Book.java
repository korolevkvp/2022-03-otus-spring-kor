package ru.otus.spring.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Long id;
    private String title;
    private Integer rating;
    private Long authorId;
    private Long genreId;

}

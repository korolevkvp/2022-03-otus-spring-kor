package ru.otus.spring.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
public class Book {

    private final Long id;
    private final String title;
    private final Integer rating;

    private final Long authorId;
    private final Long genreId;

}

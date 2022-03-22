package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BookCreate {

    private final String title;
    private final Integer rating;

    private final Long authorId;
    private final Long genreId;

}

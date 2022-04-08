package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

public interface ReaderService {

    Book readBook();

    Author readAuthor();

    Genre readGenre();

    Comment readComment();

}

package ru.otus.spring.mapper;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookCreate;

@Component
public class BookMapper {

    public Book bookCreateToBook(BookCreate bookCreate) {
        Book book = new Book();
        book.setTitle(bookCreate.getTitle());
        book.setRating(bookCreate.getRating());
        book.setAuthorId(bookCreate.getAuthorId());
        book.setGenreId(bookCreate.getGenreId());
        return book;
    }
}

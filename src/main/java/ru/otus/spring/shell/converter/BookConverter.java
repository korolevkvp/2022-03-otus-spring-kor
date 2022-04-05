package ru.otus.spring.shell.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Book;

@Component
public class BookConverter implements Converter<String, Book> {
    @Override
    public Book convert(String s) {
        String[] args = s.split(",");
        Book book = new Book();
        try {
            book.setId(Long.parseLong(args[0]));
            book.setTitle(args[1]);
            book.setRating(Integer.parseInt(args[2]));
            book.setAuthorId(Long.parseLong(args[3]));
            book.setGenreId(Long.parseLong(args[4]));
        } catch (Exception e) {
            book.setId(null);
            book.setTitle(args[0]);
            book.setRating(Integer.parseInt(args[1]));
            book.setAuthorId(Long.parseLong(args[2]));
            book.setGenreId(Long.parseLong(args[3]));
        }
        return book;
    }
}

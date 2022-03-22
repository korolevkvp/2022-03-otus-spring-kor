package ru.otus.spring.shell.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.BookCreate;

@Component
public class BookCreateConverter implements Converter<String, BookCreate> {
    @Override
    public BookCreate convert(String s) {
        String[] args = s.split(",");
        return new BookCreate(args[0], Integer.parseInt(args[1]), Long.parseLong(args[2]), Long.parseLong(args[3]));
    }
}

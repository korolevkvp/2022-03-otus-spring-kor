package ru.otus.spring.shell.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Конвертер для BookCreate")
class BookConverterTest {

    @DisplayName("должен получать BookCreate из строки")
    @Test
    void shouldGetBookCreateFromString() {
        BookConverter converter = new BookConverter();
        String string = "Buratino,8,4,1";
        Book book = new Book(null,"Buratino", 8, 4L, 1L);
        String string2 = "2,Buratino,8,4,1";
        Book book2 = new Book(2L,"Buratino", 8, 4L, 1L);

        assertThat(converter.convert(string))
                .isNotNull()
                .isInstanceOf(Book.class)
                .isEqualTo(book);
        assertThat(converter.convert(string2))
                .isNotNull()
                .isInstanceOf(Book.class)
                .isEqualTo(book2);
    }
}
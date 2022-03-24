package ru.otus.spring.shell.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.BookCreate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Конвертер для BookCreate")
class BookCreateConverterTest {

    @DisplayName("должен получать BookCreate из строки")
    @Test
    void shouldGetBookCreateFromString() {
        BookCreateConverter converter = new BookCreateConverter();
        String string = "Buratino,8,4,1";
        BookCreate bookCreate = new BookCreate("Buratino", 8, 4L, 1L);

        assertThat(converter.convert(string))
                .isNotNull()
                .isInstanceOf(BookCreate.class)
                .isEqualTo(bookCreate);
    }
}
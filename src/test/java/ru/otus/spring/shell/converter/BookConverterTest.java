package ru.otus.spring.shell.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.BookCreate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Конвертер для BookCreate")
class BookConverterTest {

    @DisplayName("должен получать BookCreate из строки")
    @Test
    void shouldGetBookCreateFromString() {
        BookConverter converter = new BookConverter();
        String string = "Buratino,8,4,1";
        BookCreate bookCreate = new BookCreate("Buratino", 8, 4L, 1L);

        assertThat(converter.convert(string))
                .isNotNull()
                .isInstanceOf(BookCreate.class)
                .isEqualTo(bookCreate);
    }
}
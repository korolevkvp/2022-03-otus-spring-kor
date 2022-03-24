package ru.otus.spring.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookCreate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Mapper для книг")
class BookMapperTest {

    @DisplayName("должен получать объект Book из BookCreate")
    @Test
    void shouldGetBookFromBookCreate() {
        BookMapper mapper = new BookMapper();
        BookCreate bookCreate = new BookCreate("Buratino", 10, 3L, 2L);

        assertThat(mapper.bookCreateToBook(bookCreate)).isNotNull().isInstanceOf(Book.class);
    }
}
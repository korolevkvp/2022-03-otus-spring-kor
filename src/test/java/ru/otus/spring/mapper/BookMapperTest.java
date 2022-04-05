package ru.otus.spring.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;

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
package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DAO класс для книг через JDBC")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc dao;

    @DisplayName("должен корректно считать количество книг")
    @Test
    void shouldCorrectCountBooks() {
        try {
            dao.deleteById(1);
        } catch (Exception ignored) {}
        int before = dao.count();
        dao.save(book());

        int after = dao.count();

        assertThat(after).isEqualTo(before +  1);
    }

    @DisplayName("должен корректно выполнять сохранение")
    @Test
    void shouldCorrectSaveBook() {
        try {
            dao.deleteById(1);
        } catch (Exception ignored) {}

        dao.save(book());

        assertThat(dao.getById(1)).isEqualTo(book());
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void deleteById() {
    }

    private Book book() {
        return new Book(1L,"Buratino", 10, 3L, 2L);
    }
}
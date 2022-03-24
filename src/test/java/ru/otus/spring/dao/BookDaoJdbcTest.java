package ru.otus.spring.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("DAO класс для книг через JDBC")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    private final long ID = 1L;

    @Autowired
    private BookDaoJdbc dao;

    @BeforeEach
    void initDeleteBook() {
        try {
            dao.deleteById(ID);
        } catch (BookNotFoundException ignored) {}
    }
    @DisplayName("должен корректно считать количество книг")
    @Test
    void shouldCorrectCountBooks() {
        int before = dao.count();
        dao.save(book());

        int after = dao.count();

        assertThat(after).isEqualTo(before +  1);
    }

    @DisplayName("должен корректно выполнять сохранение книги")
    @Test
    void shouldCorrectSaveBook() {
        dao.save(book());

        assertThat(dao.getById(ID)).isEqualTo(book());
    }

    @DisplayName("должен корректно получать книгу по идентификатору")
    @Test
    void shouldCorrectGetBookById() {
        dao.save(book());

        assertThat(dao.getById(ID)).isEqualTo(book());
    }

    @DisplayName("должен корректно получать список книг")
    @Test
    void shouldCorrectGetAllBooks() {
        dao.save(book());

        assertThat(dao.getAll()).isNotNull().contains(book());
    }

    @DisplayName("должен корректно удалять книгу по идентификатору")
    @Test
    void shouldCorrectDeleteBookById() throws BookNotFoundException {
        assertThrows(BookNotFoundException.class, () -> dao.deleteById(1));

        dao.save(book());
        dao.deleteById(ID);

        assertThrows(EmptyResultDataAccessException.class, () -> dao.getById(1));
    }

    private Book book() {
        return new Book(ID,"Buratino", 10, 3L, 2L);
    }
}
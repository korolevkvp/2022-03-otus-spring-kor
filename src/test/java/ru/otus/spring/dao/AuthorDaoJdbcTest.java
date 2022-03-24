package ru.otus.spring.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.AuthorNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("DAO класс для авторов через JDBC")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    private final long ID = 1L;

    @Autowired
    private AuthorDaoJdbc dao;

    @BeforeEach
    void initDeleteAuthor() {
        try {
            dao.deleteById(ID);
        } catch (AuthorNotFoundException ignored) {}
    }

    @DisplayName("должен корректно считать количество авторов")
    @Test
    void shouldCorrectCountAuthors() {
        int before = dao.count();
        dao.save(author());

        int after = dao.count();

        assertThat(after).isEqualTo(before +  1);
    }

    @DisplayName("должен корректно выполнять сохранение автора")
    @Test
    void shouldCorrectSaveAuthor() {
        dao.save(author());

        assertThat(dao.getById(ID)).isEqualTo(author());
    }

    @DisplayName("должен корректно получать автора по идентификатору")
    @Test
    void shouldCorrectGetAuthorById() {
        dao.save(author());

        assertThat(dao.getById(ID)).isEqualTo(author());
    }

    @DisplayName("должен корректно получать список авторов")
    @Test
    void shouldCorrectGetAllAuthors() {
        dao.save(author());

        assertThat(dao.getAll()).isNotNull().contains(author());
    }

    @DisplayName("должен корректно удалять автора по идентификатору")
    @Test
    void shouldCorrectDeleteAuthorById() throws AuthorNotFoundException {
        assertThrows(AuthorNotFoundException.class, () -> dao.deleteById(1));

        dao.save(author());
        dao.deleteById(ID);

        assertThrows(EmptyResultDataAccessException.class, () -> dao.getById(1));
    }

    private Author author() {
        return new Author(ID,"Hans", "Anderson");
    }
}
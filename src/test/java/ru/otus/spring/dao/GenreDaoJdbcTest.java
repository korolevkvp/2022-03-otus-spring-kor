package ru.otus.spring.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("DAO класс для жанров через JDBC")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    private final long ID = 1L;

    @Autowired
    private GenreDaoJdbc dao;

    @BeforeEach
    void initDeleteGenre() {
        try {
            dao.deleteById(ID);
        } catch (GenreNotFoundException ignored) {}
    }
    @DisplayName("должен корректно считать количество жанров")
    @Test
    void shouldCorrectCountGenres() {
        int before = dao.count();
        dao.save(genre());

        int after = dao.count();

        assertThat(after).isEqualTo(before +  1);
    }

    @DisplayName("должен корректно выполнять сохранение жанра")
    @Test
    void shouldCorrectSaveGenre() {
        dao.save(genre());

        assertThat(dao.getById(ID)).isEqualTo(genre());
    }

    @DisplayName("должен корректно получать жанр по идентификатору")
    @Test
    void shouldCorrectGetGenreById() {
        dao.save(genre());

        assertThat(dao.getById(ID)).isEqualTo(genre());
    }

    @DisplayName("должен корректно получать список жанров")
    @Test
    void shouldCorrectGetAllGenres() {
        dao.save(genre());

        assertThat(dao.getAll()).isNotNull().contains(genre());
    }

    @DisplayName("должен корректно удалять жанр по идентификатору")
    @Test
    void shouldCorrectDeleteGenreById() throws GenreNotFoundException {
        assertThrows(GenreNotFoundException.class, () -> dao.deleteById(1));

        dao.save(genre());
        dao.deleteById(ID);

        assertThrows(EmptyResultDataAccessException.class, () -> dao.getById(1));
    }

    private Genre genre() {
        return new Genre(ID,"novel");
    }
}
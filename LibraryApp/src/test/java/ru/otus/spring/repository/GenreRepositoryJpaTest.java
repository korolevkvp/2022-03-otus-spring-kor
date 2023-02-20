package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Репозиторий на основе Jpa для работы с жанрами")
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepositoryJpa repositoryJpa;

    @DisplayName("должен получать список жанров по названию")
    @Test
    void findByName() {
        Genre genre = repositoryJpa.save(genre());

        assertThat(repositoryJpa.findAllByName(genre.getName())).usingElementComparatorIgnoringFields("id").contains(genre);
    }

    @DisplayName("должен корректно выполнять сохранение жанра")
    @Test
    void shouldCorrectSaveGenre() {
        Genre genre = repositoryJpa.save(genre());

        assertThat(genre.getId()).isNotNull();
        assertThat(repositoryJpa.findAll()).usingElementComparatorIgnoringFields("id").contains(genre());
    }

    @DisplayName("должен корректно получать жанр по идентификатору")
    @Test
    void shouldCorrectFindGenreById() {
        Genre genre = repositoryJpa.save(genre());

        Genre testGenre = genre();
        testGenre.setId(genre.getId());
        assertThat(repositoryJpa.findById(genre.getId()).get()).isEqualTo(testGenre);
    }

    @DisplayName("должен корректно получать список жанров")
    @Test
    void shouldCorrectFindAllGenres() {
        repositoryJpa.save(genre());

        assertThat(repositoryJpa.findAll()).isNotNull().usingElementComparatorIgnoringFields("id").contains(genre());
    }

    @DisplayName("должен корректно удалять жанр по идентификатору")
    @Test
    void shouldCorrectDeleteGenreById() {
        Genre genre = repositoryJpa.save(genre());

        repositoryJpa.deleteById(genre.getId());

        assertThat(repositoryJpa.findAll()).doesNotContain(genre());
    }

    private Genre genre() {
        return new Genre(4L, "Buratino");
    }

}
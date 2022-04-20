package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с авторами")
@DataJpaTest
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepositoryJpa repositoryJpa;


    @DisplayName("должен корректно выполнять сохранение автора")
    @Test
    void shouldCorrectSaveAuthor() {
        Author author = repositoryJpa.save(author());

        assertThat(author.getId()).isNotNull();
        assertThat(repositoryJpa.findAll()).usingElementComparatorIgnoringFields("id").contains(author());
    }

    @DisplayName("должен корректно получать автора по идентификатору")
    @Test
    void shouldCorrectFindAuthorById() {
        Author author = repositoryJpa.save(author());

        Author testAuthor = author();
        testAuthor.setId(author.getId());
        assertThat(repositoryJpa.findById(author.getId()).get()).isEqualTo(testAuthor);
    }

    @DisplayName("должен корректно получать список авторов")
    @Test
    void shouldCorrectFindAllAuthors() {
        repositoryJpa.save(author());

        assertThat(repositoryJpa.findAll()).isNotNull().usingElementComparatorIgnoringFields("id").contains(author());
    }

    @DisplayName("должен корректно удалять автора по идентификатору")
    @Test
    void shouldCorrectDeleteAuthorById() {
        Author author = repositoryJpa.save(author());

        repositoryJpa.deleteById(author.getId());

        assertThat(repositoryJpa.findAll()).doesNotContain(author());
    }

    private Author author() {
        return new Author(4L, "Buratino", "Wooden");
    }

}
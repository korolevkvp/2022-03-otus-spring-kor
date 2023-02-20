package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с авторами")
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepositoryJpa authorRepository;


    @DisplayName("должен корректно выполнять сохранение автора")
    @Test
    void shouldCorrectSaveAuthor() {
        Author author = authorRepository.save(author());

        assertThat(author.getId()).isNotNull();
        assertThat(authorRepository.findAll()).usingElementComparatorIgnoringFields("id").contains(author());
    }

    @DisplayName("должен корректно получать автора по идентификатору")
    @Test
    void shouldCorrectFindAuthorById() {
        Author author = authorRepository.save(author());

        Author testAuthor = author();
        testAuthor.setId(author.getId());
        assertThat(authorRepository.findById(author.getId()).get()).isEqualTo(testAuthor);
    }

    @DisplayName("должен корректно получать список авторов")
    @Test
    void shouldCorrectFindAllAuthors() {
        authorRepository.save(author());

        assertThat(authorRepository.findAll()).isNotNull().usingElementComparatorIgnoringFields("id").contains(author());
    }

    @DisplayName("должен корректно удалять автора по идентификатору")
    @Test
    void shouldCorrectDeleteAuthorById() {
        Author author = authorRepository.save(author());

        authorRepository.deleteById(author.getId());

        assertThat(authorRepository.findAll()).doesNotContain(author());
    }

    private Author author() {
        return new Author(1L, "Buratino", "Wooden");
    }

}
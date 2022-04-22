package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книгами")
@DataJpaTest
class BookRepositoryJpaTest {

    @Autowired
    private BookRepositoryJpa repositoryJpa;

    @DisplayName("должен получать список книг по названию")
    @Test
    void findByTitle() {
        Book book = repositoryJpa.save(book());

        assertThat(repositoryJpa.findAllByTitle(book.getTitle())).usingElementComparatorIgnoringFields("id").contains(book);
    }

    @DisplayName("должен обновлять название книги, которая находится по идентификатору")
    @Test
    void updateTitleById() {
        Book book = repositoryJpa.save(book());
        String TITLE = "title";

        repositoryJpa.updateTitleById(book.getId(), TITLE);

        book.setTitle(TITLE);
        assertThat(repositoryJpa.findAll()).contains(book);
    }


    @DisplayName("должен корректно выполнять сохранение книги")
    @Test
    void shouldCorrectSaveBook() {
        Book book = repositoryJpa.save(book());

        assertThat(book.getId()).isNotNull();
        assertThat(repositoryJpa.findAll()).usingElementComparatorIgnoringFields("id").contains(book());
    }

    @DisplayName("должен корректно получать книгу по идентификатору")
    @Test
    void shouldCorrectFindBookById() {
        Book book = repositoryJpa.save(book());

        Book testBook = book();
        testBook.setId(book.getId());
        assertThat(repositoryJpa.findById(book.getId()).get()).isEqualTo(testBook);
    }

    @DisplayName("должен корректно получать список книг")
    @Test
    void shouldCorrectFindAllBooks() {
        repositoryJpa.save(book());

        assertThat(repositoryJpa.findAll()).isNotNull().usingElementComparatorIgnoringFields("id").contains(book());
    }

    @DisplayName("должен корректно удалять книгу по идентификатору")
    @Test
    void shouldCorrectDeleteBookById() {
        Book book = repositoryJpa.save(book());

        repositoryJpa.deleteById(book.getId());

        assertThat(repositoryJpa.findAll()).doesNotContain(book());
    }

    private Book book() {
        return new Book(4L, "Buratino", 10, null, null, null);
    }
}
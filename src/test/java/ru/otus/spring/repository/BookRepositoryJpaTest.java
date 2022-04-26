package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книгами")
@DataJpaTest
class BookRepositoryJpaTest {

    @Autowired
    private BookRepositoryJpa repositoryJpa;

    @Autowired
    private CommentRepositoryJpa commentRepository;

    @DisplayName("должен получать список книг по названию")
    @Test
    void shouldCorrectFindByTitle() {
        Book book = repositoryJpa.save(book());

        assertThat(repositoryJpa.findAllByTitle(book.getTitle())).usingElementComparatorIgnoringFields("id").contains(book);
    }

    @DisplayName("должен обновлять название книги, которая находится по идентификатору")
    @Test
    void shouldCorrectUpdateTitleById() {
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
        assertThat(repositoryJpa.findAll()).usingElementComparatorIgnoringFields("id", "comments").contains(book());
    }

    @DisplayName("должен корректно получать книгу по идентификатору")
    @Test
    void shouldCorrectFindBookById() {
        Book book = book();
        book.setComments(null);
        book = repositoryJpa.save(book);

        Book testBook = book;

        assertThat(repositoryJpa.findById(book.getId()).get()).isEqualTo(testBook);
    }

    @DisplayName("должен корректно получать список книг")
    @Test
    void shouldCorrectFindAllBooks() {
        repositoryJpa.save(book());

        assertThat(repositoryJpa.findAll()).isNotNull().usingElementComparatorIgnoringFields("id", "comments").contains(book());
    }

    @DisplayName("должен корректно удалять книгу по идентификатору")
    @Test
    void shouldCorrectDeleteBookById() {
        Book book = repositoryJpa.save(book());

        assertThat(commentRepository.findAll()).usingElementComparatorIgnoringFields("id").contains(new Comment(9L, "Kim", "Khm"));
        repositoryJpa.deleteById(book.getId());

        assertThat(repositoryJpa.findAll()).doesNotContain(book());
        System.out.println("commentRepository.findAll() = " + commentRepository.findAll());
        assertThat(commentRepository.findAll()).usingElementComparatorIgnoringFields("id").doesNotContain(new Comment(9L, "Kim", "Khm"));
    }

    private Book book() {
        return new Book(4L, "Buratino", 10, null, null, List.of(new Comment(9L, "Kim", "Khm")));
    }
}
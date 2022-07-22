package ru.otus.spring.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книгами")
@SpringBootTest
@ActiveProfiles("test")
class BookRepositoryJpaTest {

    @Autowired
    private BookRepositoryJpa bookRepository;

    @Autowired
    private CommentRepositoryJpa commentRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        commentRepository.deleteAll();
    }

    @DisplayName("должен получать список книг по названию")
    @Test
    @Transactional
    void shouldCorrectFindByTitle() {
        Book book = bookRepository.save(book());

        assertThat(bookRepository.findAllByTitle(book.getTitle())).usingElementComparatorIgnoringFields("id").contains(book);
    }

    @DisplayName("должен обновлять название книги, которая находится по идентификатору")
    @Test
    @Transactional
    void shouldCorrectUpdateTitleById() {
        Book book = bookRepository.save(book());
        String TITLE = "title";

        bookRepository.updateTitleById(book.getId(), TITLE);

        book.setTitle(TITLE);
        assertThat(bookRepository.findAll()).contains(book);
    }


    @DisplayName("должен корректно выполнять сохранение книги")
    @Test
    void shouldCorrectSaveBook() {
        Book book = bookRepository.save(book());

        assertThat(book.getId()).isNotNull();
        assertThat(bookRepository.findAll()).usingElementComparatorIgnoringFields("id", "comments").contains(book());
    }

    @DisplayName("должен корректно получать книгу по идентификатору")
    @Test
    @Transactional
    void shouldCorrectFindBookById() {
        Book book = book();
        book.setComments(null);
        book = bookRepository.save(book);

        Book testBook = book;

        assertThat(bookRepository.findById(book.getId()).get()).isEqualTo(testBook);
    }

    @DisplayName("должен корректно получать список книг")
    @Test
    void shouldCorrectFindAllBooks() {
        bookRepository.save(book());

        assertThat(bookRepository.findAll()).isNotNull().usingElementComparatorIgnoringFields("id", "comments").contains(book());
    }

    @DisplayName("должен корректно удалять книгу по идентификатору")
    @Test
    void shouldCorrectDeleteBookById() {
        Book book = bookRepository.save(book());
        assertThat(commentRepository.findAll()).usingElementComparatorIgnoringFields("id").contains(new Comment(9L, "Kim", "Khm"));

        bookRepository.deleteById(book.getId());

        assertThat(bookRepository.findAll()).doesNotContain(book());
        System.out.println("commentRepository.findAll() = " + commentRepository.findAll());
        assertThat(commentRepository.findAll()).usingElementComparatorIgnoringFields("id").doesNotContain(new Comment(9L, "Kim", "Khm"));
    }

    private Book book() {
        return new Book(1L, "Buratino", 10, null, null, List.of(new Comment(9L, "Kim", "Khm")));
    }
}
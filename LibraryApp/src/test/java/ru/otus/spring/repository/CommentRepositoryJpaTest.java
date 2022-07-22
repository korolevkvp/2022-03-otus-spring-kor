package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с комментариями")
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepositoryJpa repositoryJpa;

    @DisplayName("должен получать список комментариев по автору")
    @Test
    void findByAuthor() {
        Comment comment = repositoryJpa.save(comment());

        assertThat(repositoryJpa.findAllByAuthor(comment.getAuthor())).usingElementComparatorIgnoringFields("id").contains(comment);
    }

    @DisplayName("должен корректно выполнять сохранение комментария")
    @Test
    void shouldCorrectSaveComment() {
        Comment comment = repositoryJpa.save(comment());

        assertThat(comment.getId()).isNotNull();
        assertThat(repositoryJpa.findAll()).usingElementComparatorIgnoringFields("id").contains(comment());
    }

    @DisplayName("должен корректно получать комментарий по идентификатору")
    @Test
    void shouldCorrectFindCommentById() {
        Comment comment = repositoryJpa.save(comment());

        Comment testComment = comment();
        testComment.setId(comment.getId());
        assertThat(repositoryJpa.findById(comment.getId()).get()).isEqualTo(testComment);
    }

    @DisplayName("должен корректно получать список комментариев")
    @Test
    void shouldCorrectFindAllComments() {
        repositoryJpa.save(comment());

        assertThat(repositoryJpa.findAll()).isNotNull().usingElementComparatorIgnoringFields("id").contains(comment());
    }

    @DisplayName("должен корректно удалять комментарий по идентификатору")
    @Test
    void shouldCorrectDeleteCommentById() {
        Comment comment = repositoryJpa.save(comment());

        repositoryJpa.deleteById(comment.getId());

        assertThat(repositoryJpa.findAll()).doesNotContain(comment());
    }

    private Comment comment() {
        return new Comment(4L, "Buratino", "Great!");
    }

    
}
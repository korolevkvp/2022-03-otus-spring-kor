package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.exception.CommentNotFoundException;
import ru.otus.spring.repository.CommentRepositoryJpa;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@DisplayName("Сервис для работы с комментариями")
@ExtendWith(MockitoExtension.class)
class CommentConsoleServiceTest {

    private final Long ID = 5L;
    
    @Mock
    private CommentRepositoryJpa repositoryJpa;

    @InjectMocks
    private CommentConsoleService consoleService;

    @DisplayName("должен корректно получать список комментариев")
    @Test
    void findAll() {
        Comment comment1 = comment();
        Comment comment2 = comment();
        comment1.setContent("first");
        comment2.setContent("second");

        given(repositoryJpa.findAll()).willReturn(List.of(comment1, comment2));

        assertThat(consoleService.findAll()).isEqualTo(List.of(comment1, comment2));
    }

    @DisplayName("должен корректно получать комментарий по идентификатору")
    @Test
    void findById() throws CommentNotFoundException {
        given(repositoryJpa.findById(eq(ID)))
                .willReturn(Optional.of(comment()));

        assertThat(consoleService.findById(ID)).isNotNull().isEqualTo(comment());
    }

    private Comment comment() {
        return new Comment(ID, "author", "content");
    }
}
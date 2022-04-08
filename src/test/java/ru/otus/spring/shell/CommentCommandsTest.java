package ru.otus.spring.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.exception.CommentNotFoundException;
import ru.otus.spring.service.CommentConsoleService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;


@DisplayName("Shell класс для работы с комментариями")
@ExtendWith(MockitoExtension.class)
class CommentCommandsTest {

    private final Long ID = 5L;

    @Mock
    private CommentConsoleService consoleService;

    @InjectMocks
    private CommentCommands commands;

    @DisplayName("должен корректно получать список комментариев")
    @Test
    void findAll() {
        Comment comment1 = comment();
        Comment comment2 = comment();
        comment1.setContent("first");
        comment2.setContent("second");

        given(consoleService.findAll()).willReturn(List.of(comment1, comment2));

        assertThat(commands.findAll()).isEqualTo(List.of(comment1, comment2));
    }

    @DisplayName("должен корректно получать комментарий по идентификатору")
    @Test
    void findById() throws CommentNotFoundException {
        given(consoleService.findById(eq(ID)))
                .willReturn(comment());

        assertThat(commands.findById(ID)).isNotNull().isEqualTo(comment());
    }

    private Comment comment() {
        return new Comment(ID, "author", "content");
    }
}
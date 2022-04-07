package ru.otus.spring.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.AuthorNotFoundException;
import ru.otus.spring.service.AuthorConsoleService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;


@DisplayName("Shell класс для работы с авторами")
@ExtendWith(MockitoExtension.class)
class AuthorCommandsTest {

    private final Long ID = 5L;

    @Mock
    private AuthorConsoleService consoleService;

    @InjectMocks
    private AuthorCommands commands;

    @DisplayName("должен корректно получать список авторов")
    @Test
    void findAll() {
        Author author1 = author();
        Author author2 = author();
        author1.setName("first");
        author2.setName("second");

        given(consoleService.findAll()).willReturn(List.of(author1, author2));

        assertThat(commands.findAll()).isEqualTo(List.of(author1, author2));
    }

    @DisplayName("должен корректно получать автора по идентификатору")
    @Test
    void findById() throws AuthorNotFoundException {
        given(consoleService.findById(eq(ID)))
                .willReturn(author());

        assertThat(commands.findById(ID)).isNotNull().isEqualTo(author());
    }

    private Author author() {
        return new Author(ID, "name", "surname");
    }
}
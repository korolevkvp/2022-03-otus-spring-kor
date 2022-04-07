package ru.otus.spring.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookNotFoundException;
import ru.otus.spring.service.BookConsoleService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;


@DisplayName("Shell класс для работы с книгами")
@ExtendWith(MockitoExtension.class)
class BookCommandsTest {

    private final Long ID = 5L;

    @Mock
    private BookConsoleService consoleService;

    @InjectMocks
    private BookCommands commands;

    @DisplayName("должен корректно получать список книг")
    @Test
    void findAll() {
        Book book1 = book();
        Book book2 = book();
        book1.setTitle("first");
        book2.setTitle("second");

        given(consoleService.findAll()).willReturn(List.of(book1, book2));

        assertThat(commands.findAll()).isEqualTo(List.of(book1, book2));
    }

    @DisplayName("должен корректно получать книгу по идентификатору")
    @Test
    void findById() throws BookNotFoundException {
        given(consoleService.findById(eq(ID)))
                .willReturn(book());

        assertThat(commands.findById(ID)).isNotNull().isEqualTo(book());
    }

    private Book book() {
        return new Book(ID, "title", 10, null, null, null);
    }
}
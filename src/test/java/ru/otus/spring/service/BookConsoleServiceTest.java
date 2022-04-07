package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookNotFoundException;
import ru.otus.spring.repository.BookRepositoryJpa;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@DisplayName("Сервис для работы с книгами")
@ExtendWith(MockitoExtension.class)
class BookConsoleServiceTest {

    private final Long ID = 5L;
    
    @Mock
    private BookRepositoryJpa repositoryJpa;

    @InjectMocks
    private BookConsoleService consoleService;

    @DisplayName("должен корректно получать список книг")
    @Test
    void findAll() {
        Book book1 = book();
        Book book2 = book();
        book1.setTitle("first");
        book2.setTitle("second");

        given(repositoryJpa.findAll()).willReturn(List.of(book1, book2));

        assertThat(consoleService.findAll()).isEqualTo(List.of(book1, book2));
    }

    @DisplayName("должен корректно получать книгу по идентификатору")
    @Test
    void findById() throws BookNotFoundException {
        given(repositoryJpa.findById(eq(ID)))
                .willReturn(Optional.of(book()));

        assertThat(consoleService.findById(ID)).isNotNull().isEqualTo(book());
    }

    private Book book() {
        return new Book(ID, "title", 10, null, null, null);
    }
}
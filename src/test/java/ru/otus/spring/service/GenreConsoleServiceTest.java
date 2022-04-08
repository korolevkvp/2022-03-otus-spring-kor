package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreNotFoundException;
import ru.otus.spring.repository.GenreRepositoryJpa;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@DisplayName("Сервис для работы с жанрами")
@ExtendWith(MockitoExtension.class)
class GenreConsoleServiceTest {

    private final Long ID = 5L;
    
    @Mock
    private GenreRepositoryJpa repositoryJpa;

    @InjectMocks
    private GenreConsoleService consoleService;

    @DisplayName("должен корректно получать список жанров")
    @Test
    void findAll() {
        Genre genre1 = genre();
        Genre genre2 = genre();
        genre1.setName("first");
        genre2.setName("second");

        given(repositoryJpa.findAll()).willReturn(List.of(genre1, genre2));

        assertThat(consoleService.findAll()).isEqualTo(List.of(genre1, genre2));
    }

    @DisplayName("должен корректно удалять жанр по идентификатору")
    @Test
    void findById() throws GenreNotFoundException {
        given(repositoryJpa.findById(eq(ID)))
                .willReturn(Optional.of(genre()));

        assertThat(consoleService.findById(ID)).isNotNull().isEqualTo(genre());
    }

    private Genre genre() {
        return new Genre(ID, "name");
    }
}
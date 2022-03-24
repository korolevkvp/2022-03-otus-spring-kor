package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Question сервис")
class QuestionServiceImplTest {

    private final String fileName = "questions.csv";
    private final int winScore = 5;
    private QuestionServiceImpl service;

    @BeforeEach
    void initService() {
        service = new QuestionServiceImpl(Mockito.mock(MessageSource.class));
    }

    @DisplayName("Должен возвращать список вопросов")
    @Test
    void shouldReturnListOfQuestions() throws IOException {
        assertThrows(NullPointerException.class, service::getQuestions);
        service.setFileName(fileName);

        assertThat(service.getQuestions()).isNotNull().isInstanceOf(List.class);
    }

    @DisplayName("Должен корректно устанавливать количество победных очков")
    @Test
    void shouldCorrectStartQuiz() {
        service.setFileName(fileName);

        assertDoesNotThrow(() -> service.startQuiz());
    }

    @DisplayName("Должен корректно устанавливать имя файла")
    @Test
    void shouldCorrectSetFileName() {
        service.setFileName(fileName);
        String input = "2\n4\n1\n200\n0\n3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThat(service.getFileName()).isEqualTo(fileName);
    }

    @DisplayName("Должен корректно устанавливать количество победных очков")
    @Test
    void shouldCorrectSetWinScore() {
        service.setWinScore(winScore);

        assertThat(service.getWinScore()).isEqualTo(winScore);
    }


}
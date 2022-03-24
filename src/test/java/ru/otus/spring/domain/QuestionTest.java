package ru.otus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс Question")
class QuestionTest {

    @DisplayName("должен корректно создавать объект конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Question question = question();

        assertEquals("1+1", question.getQuestion());
        assertEquals("2", question.getAnswer());
    }

    @DisplayName("должен корректно возвращать вопрос")
    @Test
    void shouldReturnCorrectQuestion() {
        Question question = question();

        assertEquals("1+1", question.getQuestion());
    }

    @DisplayName("должен корректно возвращать ответ")
    @Test
    void shouldReturnCorrectAnswer() {
        Question question = question();

        assertEquals("2", question.getAnswer());
    }

    private Question question() {
        return new Question("1+1", "2");
    }
}
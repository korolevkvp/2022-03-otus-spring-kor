package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс QuestionReader")
class QuestionReaderTest {

    @DisplayName("должен возвращать список вопросов при указании правильного файла")
    @Test
    void shouldReturnListOfQuestions() throws IOException {
        assertNotNull(QuestionReader.readQuestions("/questions.csv"));
    }

    @DisplayName("должен выбрасывать исключение FileNotFoundException, если на вход даётся null")
    @Test
    void shouldThrowFileNotFoundException() {
        assertThrows(FileNotFoundException.class, () -> QuestionReader.readQuestions(null));
    }

    @DisplayName("должен выбрасывать исключение IOException, если на вход даётся неверное имя файла")
    @Test
    void shouldThrowIOException() {
        assertThrows(IOException.class, () -> QuestionReader.readQuestions("wrong"));
    }
}
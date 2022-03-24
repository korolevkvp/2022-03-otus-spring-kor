package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Класс QuestionReader")
class QuestionReaderTest {

    @DisplayName("должен возвращать список вопросов при указании правильного файла")
    @Test
    void shouldReturnListOfQuestions() throws IOException {
        assertNotNull(QuestionReader.readQuestions("questions_en_EN.csv"));
        assertThrows(FileNotFoundException.class, () -> QuestionReader.readQuestions(null));
        assertThrows(IOException.class, () -> QuestionReader.readQuestions("wrong"));
    }

}
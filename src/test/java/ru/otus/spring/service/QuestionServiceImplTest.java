package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Question сервис")
class QuestionServiceImplTest {

    @DisplayName("должен выбрасывать исключение FileNotFoundException, если не указано имя файла")
    @Test
    void shouldThrowFileNotFoundException() {
        QuestionServiceImpl service = new QuestionServiceImpl();

        assertThrows(FileNotFoundException.class, service::getQuestions);
    }

}
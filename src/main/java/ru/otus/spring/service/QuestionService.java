package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionService {

    List<Question> getQuestions() throws IOException;

    boolean startQuiz(List<Question> questions);
}

package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.util.QuestionReader;

import java.io.IOException;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;
    private final QuestionReader reader;

    public QuestionServiceImpl(QuestionDao dao, QuestionReader reader) {
        this.dao = dao;
        this.reader = reader;
    }

    @Override
    public Question getByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public List<Question> getQuestions() throws IOException {
        return reader.readQuestions();
    }

}

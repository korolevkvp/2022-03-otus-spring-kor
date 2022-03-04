package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

public class QuestionDaoSimple implements QuestionDao {

    public Question findByName(String name) {
        return new Question(name);
    }
}

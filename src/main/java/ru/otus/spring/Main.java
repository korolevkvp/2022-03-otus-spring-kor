package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionService;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService service = context.getBean(QuestionService.class);
        try {
            List<Question> questions = service.getQuestions();
            questions.forEach(question -> System.out.println(question.getData()));
        } catch (IOException e) {
            System.out.println("Файл с вопросами не найден.");
        }
    }
}

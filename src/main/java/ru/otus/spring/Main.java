package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionService;

import java.io.IOException;
import java.util.List;

@ComponentScan
public class Main {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuestionService service = context.getBean(QuestionService.class);
        List<Question> questions = service.getQuestions();
        service.startQuiz(questions);
    }
}

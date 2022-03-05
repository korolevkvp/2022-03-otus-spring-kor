package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionService;

import java.io.IOException;
import java.util.List;

@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuestionService service = context.getBean(QuestionService.class);
        try {
            List<Question> questions = service.getQuestions();
            if (service.startQuiz(questions)) {
                System.out.println("You win quiz!");
            } else {
                System.out.println("You lose quiz.");
            }
        } catch (IOException e) {
            System.out.println("File does not exist: " + e.getMessage());
        }

    }
}

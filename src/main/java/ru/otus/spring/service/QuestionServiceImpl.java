package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.util.QuestionReader;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@PropertySource("/application.properties")
public class QuestionServiceImpl implements QuestionService {

    @Value("${questionFile}")
    private String fileName;

    @Value("${zachot}")
    private int zachot;

    @Override
    public List<Question> getQuestions() throws IOException {
        return QuestionReader.readQuestions(fileName);
    }

    @Override
    public boolean startQuiz(List<Question> questions) {
        Scanner scanner = new Scanner(System.in);
        AtomicInteger score = new AtomicInteger(0);
        questions.forEach(question -> {
            System.out.print("Question: " + question.getQuestion() + "\n$ ");
            if (scanner.nextLine().equals(question.getAnswer())) {
                System.out.println("Right answer! +1 to score");
                score.incrementAndGet();
            } else {
                System.out.println("Not right answer.");
            }
        });
        System.out.println("Total score = " + score);
        return score.get() >= zachot;
    }
}

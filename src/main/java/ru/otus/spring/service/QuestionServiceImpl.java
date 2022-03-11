package ru.otus.spring.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionReader;
import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@ConfigurationProperties(prefix = "service")
public class QuestionServiceImpl implements QuestionService {


    private String fileName;
    private int winScore;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getWinScore() {
        return winScore;
    }

    public void setWinScore(int winScore) {
        this.winScore = winScore;
    }

    @Override
    public List<Question> getQuestions() throws IOException {
        return QuestionReader.readQuestions(fileName);
    }

    @Override
    public void startQuiz(List<Question> questions) {
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
        if (score.get() >= winScore) {
            System.out.println("You win quiz!");
        } else {
            System.out.println("You lose quiz.");
        }
    }
}

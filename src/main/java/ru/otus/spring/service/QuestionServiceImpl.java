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

    private final MessageSource messageSource;

    public QuestionServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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
        Locale locale = askLocale(scanner);
        AtomicInteger score = new AtomicInteger(0);
        questions.forEach(question -> {
            System.out.print(messageSource.getMessage("question", null, locale) + question.getQuestion() + "\n$ ");
            if (scanner.nextLine().equals(question.getAnswer())) {
                System.out.println(messageSource.getMessage("right-answer", null, locale));
                score.incrementAndGet();
            } else {
                System.out.println(messageSource.getMessage("not-right-answer", null, locale));
            }
        });
        System.out.println(messageSource.getMessage("total-score", null, locale) + score);
        if (score.get() >= winScore) {
            System.out.println(messageSource.getMessage("win", null, locale));
        } else {
            System.out.println(messageSource.getMessage("lose", null, locale));
        }
    }

    private Locale askLocale(Scanner scanner) {
        Locale locale = Locale.getDefault();
        if (!(locale.equals(Locale.forLanguageTag("ru-RU")) || locale.equals(Locale.forLanguageTag("en-EN")))) {
            System.out.println("Локаль системы не предусмотрена в программе, устанавливаю язык ru-RU.");
            locale = Locale.forLanguageTag("ru-RU");
        }
        System.out.println(messageSource.getMessage(
                "ask-locale", null,
                locale
        ));
        Locale result = Locale.forLanguageTag(scanner.nextLine());
        if (!(result.equals(Locale.forLanguageTag("ru-RU")) || result.equals(Locale.forLanguageTag("en-EN")))) {
            System.out.println("Локаль не предусмотрена в программе, устанавливаю язык ru-RU.");
            return Locale.forLanguageTag("ru-RU");
        }
        return result;
    }
}

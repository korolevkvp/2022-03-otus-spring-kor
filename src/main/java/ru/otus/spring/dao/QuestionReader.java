package ru.otus.spring.dao;

import org.springframework.core.io.ClassPathResource;
import ru.otus.spring.domain.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionReader {

    public static List<Question> readQuestions(String fileName) throws IOException {
        if (fileName == null) {
            throw new FileNotFoundException("Не указан путь к файлу");
        }
        ClassPathResource resource = new ClassPathResource(fileName);
        InputStream inputStream = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayList<Question> questions = new ArrayList<>(5);
        reader.lines()
                .filter(line -> line.split(",").length == 2)
                .forEach(line -> {
                    String[] args = line.split(",");
                    Question question = new Question(args[0], args[1]);
                    questions.add(question);
                });
        return questions;
    }

}

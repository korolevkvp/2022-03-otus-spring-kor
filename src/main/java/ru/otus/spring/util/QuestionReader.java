package ru.otus.spring.util;

import org.springframework.core.io.ClassPathResource;
import ru.otus.spring.domain.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionReader {

    private final String fileName;

    public QuestionReader(String fileName) {
        this.fileName = fileName;
    }

    public List<Question> readQuestions() throws IOException {
        if (fileName == null) {
            throw new FileNotFoundException("Не указан путь к файлу");
        }
        ClassPathResource resource = new ClassPathResource(fileName);
        InputStream inputStream = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayList<Question> questions = new ArrayList<>(5);

        for (String strQuestion : reader.readLine().split(",")) {
            Question question = new Question(strQuestion);
            questions.add(question);
        }
        return questions;
    }

}

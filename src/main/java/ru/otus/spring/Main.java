package ru.otus.spring;

//import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import ru.otus.spring.domain.Person;
import ru.otus.spring.service.PersonService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        Resource questions = context.getResource("/questions.csv");
        try {
            System.out.println(questions.contentLength());
        } catch (IOException e) {
            System.out.println("Файл " + questions.getFilename() + " не найден.");
        }
    }
}

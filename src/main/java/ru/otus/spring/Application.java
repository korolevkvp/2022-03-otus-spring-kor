package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionService;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		QuestionService service = context.getBean(QuestionService.class);
		List<Question> questions = service.getQuestions();
		service.startQuiz(questions);
	}

}

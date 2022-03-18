package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.QuestionService;

import java.io.IOException;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationQuestionCommands {

    private final QuestionService questionService;

    @ShellMethod(value = "Start quiz", key = {"q", "quiz"})
    public void quiz() throws IOException {
        questionService.startQuiz(questionService.getQuestions());
    }
}

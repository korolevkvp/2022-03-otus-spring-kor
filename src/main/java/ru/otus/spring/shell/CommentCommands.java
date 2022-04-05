package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.exception.CommentNotFoundException;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.ReaderService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {

    private final CommentService commentService;
    private final ReaderService readerService;

    @ShellMethod(value = "Find all comments", key = {"c", "comments"})
    public List<Comment> findAllComments() {
        return commentService.findAll();
    }

    @ShellMethod(value = "Find comment by id", key = {"fc", "find_comment"})
    public Comment findCommentById(Long id) throws CommentNotFoundException {
        return commentService.findById(id);
    }

    @ShellMethod(value = "Delete comment by id", key = {"dc", "delete_comment"})
    public void deleteCommentById(Long id) throws CommentNotFoundException {
        commentService.deleteById(id);
    }

    @ShellMethod(value = "Create comment", key = {"cc", "create_comment"})
    public Comment createComment() {
        return commentService.create(readerService.readComment());
    }
}

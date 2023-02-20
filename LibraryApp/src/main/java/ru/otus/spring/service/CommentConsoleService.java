package ru.otus.spring.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.exception.CommentNotFoundException;
import ru.otus.spring.repository.CommentRepositoryJpa;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentConsoleService implements CommentService {

    private final CommentRepositoryJpa commentRepositoryJpa;

    private final static String HYSTRIX_MESSAGE = "Извините, сейчас мы не можем дать вам ответ";


    @HystrixCommand(commandKey = "findAll", fallbackMethod = "fallbackFindAll")
    @Override
    public List<Comment> findAll() {
        return commentRepositoryJpa.findAll();
    }

    @HystrixCommand(commandKey = "deleteById", fallbackMethod = "fallbackFindOne")
    @Override
    public void deleteById(Long id) {
        commentRepositoryJpa.deleteById(id);
    }


    @HystrixCommand(commandKey = "findById", fallbackMethod = "fallbackFindOne")
    @Override
    public Comment findById(Long id) throws CommentNotFoundException {
        Optional<Comment> b = commentRepositoryJpa.findById(id);
        if (b.isPresent()) {
            return b.get();
        } else {
            throw new CommentNotFoundException(id);
        }
    }

    @HystrixCommand(commandKey = "findByAuthor", fallbackMethod = "fallbackFindAll")
    @Override
    public List<Comment> findByAuthor(String name) {
        return commentRepositoryJpa.findAllByAuthor(name);
    }

    @HystrixCommand(commandKey = "create", fallbackMethod = "fallbackFindOne")
    @Override
    @Transactional
    public Comment create(Comment comment) {
        if (comment.getContent() == null || comment.getContent().equals("")) {
            RestTemplate rest = new RestTemplate();
            Comment newComment = rest.getForObject("http://localhost:8081/generate", Comment.class);
            if (newComment != null) comment = newComment;
        }
        comment = commentRepositoryJpa.save(comment);
        return comment;
    }

    private Comment fallbackComment() {
        Comment comment = new Comment();
        comment.setAuthor(HYSTRIX_MESSAGE);
        return comment;
    }
    public List<Comment> fallbackFindAll() {
        return Collections.singletonList(fallbackComment());
    }
    public Comment fallbackFindOne() {
        return fallbackComment();
    }
}

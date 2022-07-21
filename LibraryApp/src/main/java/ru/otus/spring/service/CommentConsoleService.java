package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.exception.CommentNotFoundException;
import ru.otus.spring.repository.CommentRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentConsoleService implements CommentService {

    private final CommentRepositoryJpa commentRepositoryJpa;


    @Override
    public List<Comment> findAll() {
        return commentRepositoryJpa.findAll();
    }

    @Override
    public void deleteById(Long id) {
        commentRepositoryJpa.deleteById(id);
    }


    @Override
    public Comment findById(Long id) throws CommentNotFoundException {
        Optional<Comment> b = commentRepositoryJpa.findById(id);
        if (b.isPresent()) {
            return b.get();
        } else {
            throw new CommentNotFoundException(id);
        }
    }

    @Override
    public List<Comment> findByAuthor(String name) {
        return commentRepositoryJpa.findAllByAuthor(name);
    }

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
}

package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        return commentRepositoryJpa.findByAuthor(name);
    }

    @Override
    @Transactional
    public Comment create(Comment comment) {
        comment = commentRepositoryJpa.save(comment);
        return comment;
    }
}

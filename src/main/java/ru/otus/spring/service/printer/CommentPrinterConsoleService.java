package ru.otus.spring.service.printer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.exception.CommentNotFoundException;
import ru.otus.spring.service.CommentService;

@Service
@RequiredArgsConstructor
public class CommentPrinterConsoleService implements CommentPrinterService {

    private final CommentService commentService;

    @Override
    @Transactional(readOnly = true)
    public void findAll() {
        System.out.println(commentService.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public void findById(Long id) {
        try {
            System.out.println(commentService.findById(id));
        } catch (CommentNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        commentService.deleteById(id);
    }

    @Override
    @Transactional
    public void create(Comment comment) {
        System.out.println(commentService.create(comment));
    }
}

package ru.otus.spring.service.printer;

import ru.otus.spring.domain.Comment;

public interface CommentPrinterService {

    void findAll();

    void findById(Long id);

    void deleteById(Long id);

    void create(Comment comment);

}

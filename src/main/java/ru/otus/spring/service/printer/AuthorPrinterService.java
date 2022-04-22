package ru.otus.spring.service.printer;

import ru.otus.spring.domain.Author;

public interface AuthorPrinterService {

    void findAll();

    void findById(Long id);

    void deleteById(Long id);

    void create(Author author);

}

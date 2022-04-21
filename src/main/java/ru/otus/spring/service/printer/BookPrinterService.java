package ru.otus.spring.service.printer;

import ru.otus.spring.domain.Book;

public interface BookPrinterService {

    void findAll();

    void findById(Long id);

    void deleteById(Long id);

    void updateById(Long id, Book book);

    void create(Book book);

}

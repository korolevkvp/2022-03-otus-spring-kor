package ru.otus.spring.service.printer;

import ru.otus.spring.domain.Genre;

public interface GenrePrinterService {

    void findAll();

    void findById(Long id);

    void deleteById(Long id);

    void create(Genre genre);

}

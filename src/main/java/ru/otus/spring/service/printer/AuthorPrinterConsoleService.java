package ru.otus.spring.service.printer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.AuthorNotFoundException;
import ru.otus.spring.service.AuthorService;

@Service
@RequiredArgsConstructor
public class AuthorPrinterConsoleService implements AuthorPrinterService {

    private final AuthorService authorService;

    @Override
    @Transactional(readOnly = true)
    public void findAll() {
        System.out.println(authorService.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public void findById(Long id) {
        try {
            System.out.println(authorService.findById(id));
        } catch (AuthorNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        authorService.deleteById(id);
    }

    @Override
    @Transactional
    public void create(Author author) {
        System.out.println(authorService.create(author));
    }
}

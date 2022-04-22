package ru.otus.spring.service.printer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreNotFoundException;
import ru.otus.spring.service.GenreService;

@Service
@RequiredArgsConstructor
public class GenrePrinterConsoleService implements GenrePrinterService {

    private final GenreService genreService;

    @Override
    @Transactional(readOnly = true)
    public void findAll() {
        System.out.println(genreService.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public void findById(Long id) {
        try {
            System.out.println(genreService.findById(id));
        } catch (GenreNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        genreService.deleteById(id);
    }

    @Override
    @Transactional
    public void create(Genre genre) {
        System.out.println(genreService.create(genre));
    }
}

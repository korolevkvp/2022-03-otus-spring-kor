package ru.otus.spring.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreNotFoundException;
import ru.otus.spring.repository.GenreRepositoryJpa;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreConsoleService implements GenreService {

    private final GenreRepositoryJpa genreRepositoryJpa;

    private final static String HYSTRIX_MESSAGE = "Извините, сейчас мы не можем дать вам ответ";

    @HystrixCommand(commandKey = "findAll", fallbackMethod = "fallbackFindAll")
    @Override
    public List<Genre> findAll() {
        return genreRepositoryJpa.findAll();
    }

    @HystrixCommand(commandKey = "findById", fallbackMethod = "fallbackFindOne")
    @Override
    public Genre findById(Long id) throws GenreNotFoundException {
        Optional<Genre> b = genreRepositoryJpa.findById(id);
        if (b.isPresent()) {
            return b.get();
        } else {
            throw new GenreNotFoundException(id);
        }
    }

    @HystrixCommand(commandKey = "findByName", fallbackMethod = "fallbackFindAll")
    @Override
    public List<Genre> findByName(String name) {
        return genreRepositoryJpa.findAllByName(name);
    }

    @HystrixCommand(commandKey = "deleteById", fallbackMethod = "fallbackFindOne")
    @Override
    @Transactional
    public void deleteById(Long id) {
        genreRepositoryJpa.deleteById(id);
    }

    @HystrixCommand(commandKey = "create", fallbackMethod = "fallbackFindOne")
    @Override
    @Transactional
    public Genre create(Genre genre) {
        genre = genreRepositoryJpa.save(genre);
        return genre;
    }

    private Genre fallbackGenre() {
        Genre genre = new Genre();
        genre.setName(HYSTRIX_MESSAGE);
        return genre;
    }

    public List<Genre> fallbackFindAll() {
        return Collections.singletonList(fallbackGenre());
    }
    public Genre fallbackFindOne() {
        return fallbackGenre();
    }
}

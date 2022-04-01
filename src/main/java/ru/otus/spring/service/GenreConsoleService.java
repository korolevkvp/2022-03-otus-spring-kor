package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreNotFoundException;
import ru.otus.spring.repository.GenreRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreConsoleService implements GenreService {

    private final GenreRepositoryJpa genreRepositoryJpa;


    @Override
    public List<Genre> findAll() {
        return genreRepositoryJpa.findAll();
    }

    @Override
    public Genre updateById(Genre genre) throws GenreNotFoundException {
        if (genreRepositoryJpa.findById(genre.getId()).isPresent()) {
            genreRepositoryJpa.deleteById(genre.getId());
        }
        genre = genreRepositoryJpa.save(genre);
        return genre;
    }

    @Override
    public Genre findById(Long id) throws GenreNotFoundException {
        Optional<Genre> b = genreRepositoryJpa.findById(id);
        if (b.isPresent()) {
            return b.get();
        } else {
            throw new GenreNotFoundException(id);
        }
    }

    @Override
    public List<Genre> findByName(String name) {
        return genreRepositoryJpa.findByName(name);
    }

    @Override
    public void deleteById(Long id) throws GenreNotFoundException {
        genreRepositoryJpa.deleteById(id);
    }

    @Override
    public Genre create(Genre genre) {
        genre = genreRepositoryJpa.save(genre);
        return genre;
    }
}

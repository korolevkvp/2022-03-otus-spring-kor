package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.AuthorNotFoundException;
import ru.otus.spring.repository.AuthorRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorConsoleService implements AuthorService {

    private final AuthorRepositoryJpa authorRepositoryJpa;


    @Override
    public List<Author> findAll() {
        return authorRepositoryJpa.findAll();
    }

    @Override
    @Transactional
    public Author updateById(Author author) throws AuthorNotFoundException {
        if (authorRepositoryJpa.findById(author.getId()).isPresent()) {
            authorRepositoryJpa.deleteById(author.getId());
        }
        author = authorRepositoryJpa.save(author);
        return author;
    }

    @Override
    public Author findById(Long id) throws AuthorNotFoundException {
        Optional<Author> b = authorRepositoryJpa.findById(id);
        if (b.isPresent()) {
            return b.get();
        } else {
            throw new AuthorNotFoundException(id);
        }
    }

    @Override
    public List<Author> findByName(String name) {
        return authorRepositoryJpa.findByName(name);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws AuthorNotFoundException {
        authorRepositoryJpa.deleteById(id);
    }

    @Override
    @Transactional
    public Author create(Author author) {
        author = authorRepositoryJpa.save(author);
        return author;
    }
}

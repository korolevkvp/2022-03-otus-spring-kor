package ru.otus.spring.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.AuthorNotFoundException;
import ru.otus.spring.repository.AuthorRepositoryJpa;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorConsoleService implements AuthorService {

    private final AuthorRepositoryJpa authorRepositoryJpa;

    private final static String HYSTRIX_MESSAGE = "Извините, сейчас мы не можем дать вам ответ";

    @HystrixCommand(commandKey = "findAll", fallbackMethod = "fallbackFindAll")
    @Override
    public List<Author> findAll() {
        return authorRepositoryJpa.findAll();
    }

    @HystrixCommand(commandKey = "findById", fallbackMethod = "fallbackFindOne")
    @Override
    public Author findById(Long id) throws AuthorNotFoundException {
        Optional<Author> b = authorRepositoryJpa.findById(id);
        if (b.isPresent()) {
            return b.get();
        } else {
            throw new AuthorNotFoundException(id);
        }
    }

    @HystrixCommand(commandKey = "findByName", fallbackMethod = "fallbackFindAll")
    @Override
    public List<Author> findByName(String name) {
        return authorRepositoryJpa.findAllByName(name);
    }

    @HystrixCommand(commandKey = "deleteById", fallbackMethod = "fallbackFindOne")
    @Override
    @Transactional
    public void deleteById(Long id) {
        authorRepositoryJpa.deleteById(id);
    }

    @HystrixCommand(commandKey = "create", fallbackMethod = "fallbackFindOne")
    @Override
    @Transactional
    public Author create(Author author) {
        author = authorRepositoryJpa.save(author);
        return author;
    }

    private Author fallbackAuthor() {
        Author author = new Author();
        author.setName(HYSTRIX_MESSAGE);
        return author;
    }
    public List<Author> fallbackFindAll() {
        return Collections.singletonList(fallbackAuthor());
    }
    public Author fallbackFindOne() {
        return fallbackAuthor();
    }
}

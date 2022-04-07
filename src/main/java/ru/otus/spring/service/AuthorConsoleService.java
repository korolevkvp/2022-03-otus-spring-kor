package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorConsoleService implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public List<Author> findAll() {
        return authorDao.getAll();
    }

}

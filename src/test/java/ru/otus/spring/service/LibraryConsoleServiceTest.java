package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.BookDaoJdbc;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Сервис библиотеки для консоли")
@JdbcTest
@Import(LibraryConsoleService.class)
class LibraryConsoleServiceTest {

    @Autowired
    private LibraryConsoleService service;
    @Test
    void findAllBooks() {
    }

    @Test
    void findAllGenres() {
    }

    @Test
    void findAllAuthors() {
    }

    @Test
    void updateBookById() {
    }

    @Test
    void findBookById() {
    }

    @Test
    void deleteBookById() {
    }

    @Test
    void createBook() {
    }
}
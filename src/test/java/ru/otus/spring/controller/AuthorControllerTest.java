package ru.otus.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repository.AuthorRepositoryJpa;
import ru.otus.spring.repository.CommentRepositoryJpa;
import ru.otus.spring.service.AuthorService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayName("Контроллер для работы с авторами")
class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AuthorRepositoryJpa repository;

    @Autowired
    private AuthorService service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("должен корректно выводить список всех авторов")
    void shouldCorrectFindAll() throws Exception {
        List<Author> repositoryExpected = repository.findAll();
        List<Author> serviceExpected = service.findAll();

        Author author = repository.save(author());
        repositoryExpected.add(author);
        serviceExpected.add(author);

        mvc.perform(get("/author/"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(repositoryExpected)))
                .andExpect(content().json(mapper.writeValueAsString(serviceExpected)));
    }

    @Test
    @DisplayName("должен корректно выводить автора по идентификатору")
    void findById() throws Exception {
        Author author = repository.save(author());

        mvc.perform(get("/author/" + author.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(author)));
    }

    @Test
    @DisplayName("должен корректно удалять автора по идентификатору")
    void deleteById() throws Exception {
        Author author = repository.save(author());

        mvc.perform(delete("/author/" + author.getId()))
                .andExpect(status().isOk());

        assertThat(repository.findAll())
                .doesNotContain(author);
    }

    @Test
    @DisplayName("должен корректно создавать автора")
    void create() throws Exception {
        String expectedResult = mapper.writeValueAsString(author());

        mvc.perform(post("/author/").contentType(MediaType.APPLICATION_JSON)
                        .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    private Author author() {
        return new Author(4L, "Greg", "House");
    }

}
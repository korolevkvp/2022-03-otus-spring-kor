package ru.otus.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repository.CommentRepositoryJpa;
import ru.otus.spring.service.CommentService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayName("Контроллер для работы с комментариями")
@ActiveProfiles("test")
class CommentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CommentRepositoryJpa repository;


    @Autowired
    private CommentService service;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("должен корректно выводить список всех комментариев")
    void shouldCorrectFindAll() throws Exception {
        List<Comment> repositoryExpected = repository.findAll();
        List<Comment> serviceExpected = service.findAll();

        Comment comment = repository.save(comment());
        repositoryExpected.add(comment);
        serviceExpected.add(comment);

        mvc.perform(get("/comment/"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(repositoryExpected)))
                .andExpect(content().json(mapper.writeValueAsString(serviceExpected)));
    }

    @Test
    @DisplayName("должен корректно выводить комментарий по идентификатору")
    void findById() throws Exception {
        Comment comment = repository.save(comment());

        mvc.perform(get("/comment/" + comment.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(comment)));
    }

    @Test
    @DisplayName("должен корректно удалять комментарий по идентификатору")
    void deleteById() throws Exception {
        Comment comment = repository.save(comment());

        mvc.perform(delete("/comment/" + comment.getId()))
                .andExpect(status().isOk());

        assertThat(repository.findAll())
                .doesNotContain(comment);
    }

    @Test
    @DisplayName("должен корректно создавать жанр")
    void create() throws Exception {
        String expectedResult = mapper.writeValueAsString(comment());

        mvc.perform(post("/comment/").contentType(MediaType.APPLICATION_JSON)
                        .content(expectedResult))
                .andExpect(status().isOk());
    }

    private Comment comment() {
        return new Comment(1L, "Michael Jordan", "Mmm, nice!");
    }

}
package ru.otus.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepositoryJpa;
import ru.otus.spring.service.GenreService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayName("Контроллер для работы с жанрами")
class GenreControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private GenreRepositoryJpa repository;


    @Autowired
    private GenreService service;

    @Autowired
    private ObjectMapper mapper;

    @WithMockUser(
            username = "asdm22in",
            value = "adsm22in",
            roles = "ADsM22IN",
            password = "passsw22ord")
    @Test
    @DisplayName("должен корректно выводить список всех жанров")
    void shouldCorrectFindAll() throws Exception {
        List<Genre> repositoryExpected = repository.findAll();
        List<Genre> serviceExpected = service.findAll();

        Genre genre = repository.save(genre());
        repositoryExpected.add(genre);
        serviceExpected.add(genre);

        mvc.perform(get("/genre/"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(repositoryExpected)))
                .andExpect(content().json(mapper.writeValueAsString(serviceExpected)));
    }

    @Test
    @DisplayName("должен выдавать другой статус неавторизованному пользователю")
    void shouldThrowAnotherStatusIfUnauthorised() throws Exception {
        mvc.perform(get("/genre"))
                .andExpect(status().isFound());
    }

    @WithMockUser(
            username = "asdm22in",
            value = "adsm22in",
            roles = "ADsM22IN",
            password = "passsw22ord")
    @Test
    @DisplayName("должен корректно выводить жанр по идентификатору")
    void findById() throws Exception {
        Genre genre = repository.save(genre());

        mvc.perform(get("/genre/" + genre.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(genre)));
    }

    @WithMockUser(
            username = "asdm22in",
            value = "adsm22in",
            roles = "ADsM22IN",
            password = "passsw22ord")
    @Test
    @DisplayName("должен корректно удалять жанр по идентификатору")
    void deleteById() throws Exception {
        Genre genre = repository.save(genre());

        mvc.perform(delete("/genre/" + genre.getId()))
                .andExpect(status().isOk());

        assertThat(repository.findAll())
                .doesNotContain(genre);
    }


    @WithMockUser(
            username = "admin",
            value = "admin",
            roles = "ADMIN",
            password = "password")
    @Test
    @DisplayName("должен корректно создавать жанр")
    void create() throws Exception {
        String expectedResult = mapper.writeValueAsString(genre());

        mvc.perform(post("/genre/").contentType(MediaType.APPLICATION_JSON)
                        .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    private Genre genre() {
        return new Genre(4L, "Chill");
    }

}
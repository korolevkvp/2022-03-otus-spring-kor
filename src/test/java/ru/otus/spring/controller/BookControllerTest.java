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
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repository.BookRepositoryJpa;
import ru.otus.spring.repository.CommentRepositoryJpa;
import ru.otus.spring.service.BookService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayName("Контроллер для работы с книгами")
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookRepositoryJpa repository;

    @Autowired
    private CommentRepositoryJpa commentRepository;

    @Autowired
    private BookService service;

    @Autowired
    private ObjectMapper mapper;

    @WithMockUser(
            username = "asdm22in",
            value = "adsm22in",
            roles = "ADsM22IN",
            password = "passsw22ord")
    @Test
    @DisplayName("должен корректно выводить список всех книг")
    void shouldCorrectFindAll() throws Exception {
        List<Book> repositoryExpected = repository.findAll();
        List<Book> serviceExpected = service.findAll();

        Book book = repository.save(book());
        repositoryExpected.add(book);
        serviceExpected.add(book);

        mvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(repositoryExpected)))
                .andExpect(content().json(mapper.writeValueAsString(serviceExpected)));
    }


    @Test
    @DisplayName("должен выдавать другой статус неавторизованному пользователю")
    void shouldThrowAnotherStatusIfUnauthorised() throws Exception {
        mvc.perform(get("/book"))
                .andExpect(status().isFound());
    }

    @WithMockUser(
            username = "33",
            value = "adsm22in",
            roles = "ADsM22IN",
            password = "passsw22ord")
    @Test
    @DisplayName("должен корректно выводить книгу по идентификатору")
    void findById() throws Exception {
        Book book = repository.save(book());

        mvc.perform(get("/book/" + book.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(book)));
    }

    @WithMockUser(
            username = "asdm44in",
            value = "adsm22in",
            roles = "ADsM22IN",
            password = "passsw22ord")
    @Test
    @DisplayName("должен корректно обновлять книгу по идентификатору")
    void updateById() throws Exception {
        Book book = book();
        book = repository.save(book);
        book.setTitle("Modified");
        String expectedResult = mapper.writeValueAsString(book);

        mvc.perform(put("/book/" + book.getId()).contentType(MediaType.APPLICATION_JSON)
                        .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @WithMockUser(
            username = "asdm55in",
            value = "adsm22in",
            roles = "ADsM22IN",
            password = "passsw22ord")
    @Test
    @DisplayName("должен корректно удалять книгу по идентификатору")
    void deleteById() throws Exception {
        Book book = repository.save(book());

        mvc.perform(delete("/book/" + book.getId()))
                .andExpect(status().isOk());

        assertThat(repository.findAll())
                .doesNotContain(book);
    }

    @WithMockUser(
            username = "asdm66in",
            value = "adsm22in",
            roles = "ADsM22IN",
            password = "passsw22ord")
    @Test
    @DisplayName("должен корректно создавать книгу")
    void create() throws Exception {
        String expectedResult = mapper.writeValueAsString(book());
        System.out.println("commentRepository.findAll() = " + commentRepository.findAll());
        mvc.perform(post("/book/").contentType(MediaType.APPLICATION_JSON)
                        .content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
        assertThat(commentRepository.findAll()).usingElementComparatorIgnoringFields("id").contains(new Comment(null, "Kim", "Khm"));
    }

    private Book book() {
        return new Book(4L, "Buratino", 10, null, null, List.of(new Comment(9L, "Kim", "Khm")));
    }

}
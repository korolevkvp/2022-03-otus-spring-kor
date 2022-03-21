package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public void insert(Book book) {
        jdbc.update("insert into books (id, `title`, `rating`, `authorId`, `genreId`) values (:id, :title, :rating, :authorId, :genreId)",
                Map.of("id", book.getId(), "title", book.getTitle(), "rating", book.getRating(), "authorId", book.getAuthorId(), "genreId", book.getAuthorId()));
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbc.queryForObject("select * from books where id = :id", params, new BookDaoJdbc.BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books", new BookDaoJdbc.BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbc.update("delete from books where id = :id", params);
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id =resultSet.getLong("id");
            String title = resultSet.getString("title");
            Integer rating = resultSet.getInt("rating");
            Long authorId = resultSet.getLong("authorId");
            Long genreId = resultSet.getLong("genreId");
            return new Book(id, title, rating, authorId, genreId);
        }
    }
}

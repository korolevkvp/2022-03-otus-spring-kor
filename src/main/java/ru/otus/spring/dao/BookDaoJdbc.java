package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public Long save(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("rating", book.getRating());
        params.addValue("authorId", book.getAuthorId());
        params.addValue("genreId", book.getGenreId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (book.getId() == null) {
            jdbc.update("insert into books (`title`, `rating`, `authorId`, `genreId`) values (:title, :rating, :authorId, :genreId)",
                   params, keyHolder);
        } else {
            params.addValue("id", book.getId());
            jdbc.update("insert into books (id, `title`, `rating`, `authorId`, `genreId`) values (:id, :title, :rating, :authorId, :genreId)",
                    params, keyHolder);
        }
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
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

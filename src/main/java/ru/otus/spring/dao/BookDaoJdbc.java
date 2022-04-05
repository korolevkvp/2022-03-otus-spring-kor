package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookNotFoundException;

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
        params.addValue("authorid", book.getAuthorId());
        params.addValue("genreid", book.getGenreId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (book.getId() == null) {
            jdbc.update("insert into books (`title`, `rating`, `authorid`, `genreid`) values (:title, :rating, :authorid, :genreid)",
                   params, keyHolder);
        } else {
            params.addValue("id", book.getId());
            jdbc.update("insert into books (id, `title`, `rating`, `authorid`, `genreid`) values (:id, :title, :rating, :authorid, :genreid)",
                    params, keyHolder);
        }
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public void update(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", book.getId());
        params.addValue("title", book.getTitle());
        params.addValue("rating", book.getRating());
        params.addValue("authorid", book.getAuthorId());
        params.addValue("genreid", book.getGenreId());
        jdbc.update("update books " +
                "set title = :title, rating = :rating, authorid = :authorid, genreid = :genreid " +
                "where id = :id", params);
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
    public void deleteById(long id) throws BookNotFoundException {
        try {
            getById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new BookNotFoundException(id);
        }
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbc.update("delete from books where id = :id", params);
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id =resultSet.getLong("id");
            String title = resultSet.getString("title");
            Integer rating = resultSet.getInt("rating");
            Long authorId = resultSet.getLong("authorid");
            Long genreId = resultSet.getLong("genreid");
            return new Book(id, title, rating, authorId, genreId);
        }
    }
}

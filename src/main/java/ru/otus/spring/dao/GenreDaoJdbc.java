package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class GenreDaoJdbc implements GenreDao{


    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from genres", Integer.class);
    }

    @Override
    public void save(Genre genre) {
        jdbc.update("insert into genres (id, `name`) values (:id, :name)",
                Map.of("id", genre.getId(), "name", genre.getName()));
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbc.queryForObject("select * from genres where id = :id", params, new GenreDaoJdbc.GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", new GenreDaoJdbc.GenreMapper());
    }

    @Override
    public void deleteById(long id) throws GenreNotFoundException {
        try {
            getById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new GenreNotFoundException(id);
        }
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbc.update("delete from genres where id = :id", params);
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id =resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}

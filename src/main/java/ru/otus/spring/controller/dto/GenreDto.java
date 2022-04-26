package ru.otus.spring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.domain.Genre;

@Data
@AllArgsConstructor
public class GenreDto {

    private Long id;

    private String name;


    public static Genre toDomainObject(GenreDto dto) {
        return new Genre(dto.getId(), dto.getName());
    }

    public static GenreDto toDto(Genre domain) {
        return new GenreDto(domain.getId(), domain.getName());
    }
}

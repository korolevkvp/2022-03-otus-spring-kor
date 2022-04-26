package ru.otus.spring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.domain.Author;

@Data
@AllArgsConstructor
public class AuthorDto {

    private Long id;

    private String name;

    private String surname;

    public static Author toDomainObject(AuthorDto dto) {
        return new Author(dto.getId(), dto.getName(), dto.getSurname());
    }

    public static AuthorDto toDto(Author domain) {
        return new AuthorDto(domain.getId(), domain.getName(), domain.getSurname());
    }
}

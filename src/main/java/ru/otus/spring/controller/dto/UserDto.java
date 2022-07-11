package ru.otus.spring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.domain.User;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String password;


    public static User toDomainObject(UserDto dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword());
    }

    public static UserDto toDto(User domain) {
        return new UserDto(domain.getId(), domain.getUsername(), domain.getPassword());
    }
}

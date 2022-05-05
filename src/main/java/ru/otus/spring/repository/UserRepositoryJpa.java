package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.User;

public interface UserRepositoryJpa extends JpaRepository<User, String> {
    User findByUsername(String username);
}

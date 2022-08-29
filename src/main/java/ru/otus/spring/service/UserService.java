package ru.otus.spring.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.otus.spring.domain.User;

public interface UserService extends UserDetailsService {

    User create(User user);

    User findByUsername(String username);

}

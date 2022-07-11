package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.User;
import ru.otus.spring.repository.UserRepositoryJpa;

import java.util.HashSet;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserConsoleService implements UserService {

    private final UserRepositoryJpa userRepositoryJpa;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User create(User user) {
        return userRepositoryJpa.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepositoryJpa.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositoryJpa.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(String.format("User %s is not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, new HashSet<>());
    }
}

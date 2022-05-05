package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.User;
import ru.otus.spring.repository.UserRepositoryJpa;

@Service
@RequiredArgsConstructor
public class UserConsoleService implements UserService {

    private final UserRepositoryJpa userRepositoryJpa;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User create(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepositoryJpa.save(user);
    }
}

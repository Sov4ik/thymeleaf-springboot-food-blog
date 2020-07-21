package com.foodblog.service;

import com.foodblog.models.Role;
import com.foodblog.models.User;
import com.foodblog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {


    private final UserRepository userRepository;

    private final PasswordEncoder encoder;


    public AuthService(UserRepository userRepository,
                       PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void registerUser(String username,
                             String password,
                             String email) {

        User userFromDb = userRepository.findByUsername(username);

        if (userFromDb != null) {
            return;
        }

        User user = new User(username,
                encoder.encode(password),
                true,
                email,
                Collections.singleton(Role.ADMIN));

        userRepository.save(user);

    }
}

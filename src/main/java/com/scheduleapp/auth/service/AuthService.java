package com.scheduleapp.auth.service;

import com.scheduleapp.auth.dto.AuthRequest;
import com.scheduleapp.entity.User;
import com.scheduleapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public void signup(AuthRequest authRequest) {
        User user = new User(
                authRequest.getName(),
                authRequest.getPassword()
        );
        userRepository.save(user);
    }
}

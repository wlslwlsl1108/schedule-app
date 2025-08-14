package com.scheduleapp.auth.service;

import com.scheduleapp.auth.dto.AuthRequest;
import com.scheduleapp.auth.dto.AuthResponse;
import com.scheduleapp.schedule.entity.User;
import com.scheduleapp.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    // 회원가입 //
    @Transactional
    public void signup(AuthRequest authRequest) {
        User user = new User(
                authRequest.getEmail(),
                authRequest.getPassword()
        );
        userRepository.save(user);
    }

    // 로그인 //
    @Transactional(readOnly = true)
    public AuthResponse login(AuthRequest authRequest) {
        User user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "이메일이 틀렸습니다.")
        );

        if (!Objects.equals(authRequest.getPassword(), user.getPassword())){
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다.");
        }
        return new AuthResponse(user.getEmail());
    }

}

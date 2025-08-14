package com.scheduleapp.auth.service;

import com.scheduleapp.auth.dto.AuthRequest;                      // 요청 DTO
import com.scheduleapp.auth.dto.AuthResponse;                     // 응답 DTO
import com.scheduleapp.schedule.entity.User;                      // User entity
import com.scheduleapp.schedule.repository.UserRepository;        // User repository
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;    // 상태코드 있는 예외

import java.util.Objects;

@Service                       // 서비스 빈 등록
@RequiredArgsConstructor       // uerRepository 주입
public class AuthService {

    private final UserRepository userRepository;        // 유저 저장, 조회 의존성

    // 회원가입 //
    @Transactional                                      // 트랜잭션(작성)
    public void signup(AuthRequest authRequest) {
        User user = new User(                           // 이름, 이메일, 비밀번호로 User 생성
                authRequest.getName(),
                authRequest.getEmail(),
                authRequest.getPassword()
        );
        userRepository.save(user);                      // save -> INSERT
    }

    // 로그인 //
    @Transactional(readOnly = true)                                                    // 트랜잭션(읽기 전용)
    public AuthResponse login(AuthRequest authRequest) {
        User user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow(
                () -> new ResponseStatusException(                                     // 이메일 불일치 -> 401 반환
                        HttpStatus.UNAUTHORIZED, "이메일이 틀렸습니다.")
        );

        if (!Objects.equals(authRequest.getPassword(), user.getPassword())){           // 비밀번호 불일치 -> 401 반환
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다.");
        }
        return new AuthResponse(user.getName(),user.getEmail());                       // 인증 성공 시 이름, 이메일 담아 반환
    }

}

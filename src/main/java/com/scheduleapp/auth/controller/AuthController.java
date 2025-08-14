package com.scheduleapp.auth.controller;

import com.scheduleapp.auth.dto.AuthRequest;                   // 로그인, 회원가입 요청 DTO
import com.scheduleapp.auth.dto.AuthResponse;                  // 로그인 성공 시 응답 DTO
import com.scheduleapp.auth.service.AuthService;               // 인증 비즈니스 로직 서비스
import jakarta.servlet.http.HttpServletRequest;                // 세션 발급 위해 요청 객체 사용
import jakarta.servlet.http.HttpSession;                       // HttpSession 사용
import lombok.RequiredArgsConstructor;                         // final 필드 자동 생성자
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController          // REST 컨트롤러 -> 메서드 반환값을 JSON/문자열로 반환
@RequiredArgsConstructor    // AuthService 주입용 생성자 자동 생성
public class AuthController {

    private final AuthService authService;          // 인증 서비스 의존성

    // 회원가입 //
    @PostMapping("/signup")
    public String signup(
            @RequestBody AuthRequest authRequest    // 요청 본문(JSON)을 AuthRequest로 바인딩
    ) {
        authService.signup(authRequest);            // 회원가입 로직 실행
        return "회원가입에 성공했습니다.";              // 문자열 응답(200 OK)
    }

    // 로그인 //
    @PostMapping("/login")
    public String login(
            @RequestBody AuthRequest authRequest,                      // 이메일, 비밀번호 받기
            HttpServletRequest request                                 // 세션 발급 위해 HttpServleetRequest 주입
    ) {
        // Cookie Session 발급 //
        AuthResponse result = authService.login(authRequest);          // 이메일, 비밀번호 검증

        HttpSession session = request.getSession();                    // 세션 가져오기 -> 없으면 생성
        session.setAttribute("LOGIN_DIRECTOR", result.getEmail());  // 세션에 로그인 사용자 이메일 저장
        return "로그인에 성공했습니다.";                                   // 문자열 응답(200 OK)
    }
}

package com.scheduleapp.auth.controller;

import com.scheduleapp.auth.dto.AuthRequest;
import com.scheduleapp.auth.dto.AuthResponse;
import com.scheduleapp.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입 //
    @PostMapping("/signup")
    public String signup(
            @RequestBody AuthRequest authRequest
    ) {
        authService.signup(authRequest);
        return "회원가입에 성공했습니다.";
    }

    // 로그인 //
    @PostMapping("/login")
    public String login(
            @RequestBody AuthRequest authRequest,
            HttpServletRequest request
    ) {
        // Cookie Session 발급 //
        AuthResponse result = authService.login(authRequest);

        HttpSession session = request.getSession();
        session.setAttribute("LOGIN_DIRECTOR", result.getEmail());
        return "로그인에 성공했습니다.";
    }
}

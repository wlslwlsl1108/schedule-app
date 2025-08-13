package com.scheduleapp.auth.controller;

import com.scheduleapp.auth.dto.AuthRequest;
import com.scheduleapp.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public String signup(
            @RequestBody AuthRequest authRequest
    ) {
        authService.signup(authRequest);
        return "회원가입에 성공했습니다.";
    }
}

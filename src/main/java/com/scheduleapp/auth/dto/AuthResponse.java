package com.scheduleapp.auth.dto;

import lombok.Getter;

@Getter
public class AuthResponse {

    private final String name;      // 로그인 성공 -> 이름, 이메일 반환
    private final String email;

    public AuthResponse(String name, String email) {
        this.name = name;           // 불변 필드 초기화
        this.email = email;
    }
}

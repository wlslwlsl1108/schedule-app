package com.scheduleapp.auth.dto;

import lombok.Getter;

@Getter
public class AuthResponse {

    private final String email;

    public AuthResponse(String email) {
        this.email = email;
    }
}

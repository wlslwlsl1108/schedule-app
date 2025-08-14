package com.scheduleapp.auth.dto;

import lombok.Getter;

@Getter
public class AuthResponse {

    private final String name;
    private final String email;

    public AuthResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

package com.scheduleapp.auth.dto;

import lombok.Getter;

@Getter
public class AuthResponse {

    private final Long id;

    public AuthResponse(Long id) {
        this.id = id;
    }
}

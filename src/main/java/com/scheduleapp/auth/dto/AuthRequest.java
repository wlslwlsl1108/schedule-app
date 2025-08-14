package com.scheduleapp.auth.dto;

import lombok.Getter;

@Getter
public class AuthRequest {

    private String name;
    private String email;
    private String password;
}

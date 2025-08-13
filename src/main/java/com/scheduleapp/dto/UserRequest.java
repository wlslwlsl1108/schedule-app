package com.scheduleapp.dto;

import lombok.Getter;

import java.security.Principal;

@Getter
public class UserRequest {
    private String name;
    private String email;
}

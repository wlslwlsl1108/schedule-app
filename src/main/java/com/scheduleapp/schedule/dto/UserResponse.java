package com.scheduleapp.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponse {

    private final Long id;                   // 유저 ID
    private final String name;               // 유저 이름
    private final String email;              // 유저 이메일
    private final LocalDateTime createdAt;   // 생성일(자동생성)
    private final LocalDateTime updatedAt;   // 수정일(자동생성)

    public UserResponse(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

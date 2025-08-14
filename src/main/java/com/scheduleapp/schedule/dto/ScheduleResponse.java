package com.scheduleapp.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponse {

    private final Long id;                  // 일정 ID
    private final String title;             // 일정 제목
    private final String content;           // 일정 내용
    private final LocalDateTime createdAt;  // 생성일(자동생성)
    private final LocalDateTime updatedAt;  // 수정일(자동생성)

    public ScheduleResponse(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

package com.scheduleapp.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateResponse {

    private Long id;             // 일정 ID
    private String title;        // 수정된 일정 제목
    private String content;      // 수정된 일정 내용

    public ScheduleUpdateResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}

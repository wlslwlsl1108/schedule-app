package com.scheduleapp.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateResponse {

    private Long id;
    private String title;
    private String content;

    public ScheduleUpdateResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}

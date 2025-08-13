package com.scheduleapp.dto;

import com.scheduleapp.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleUpdateResponse {

    private Long id;
    private String title;
    private String name;

    public ScheduleUpdateResponse(Long id, String title, String name) {
        this.id = id;
        this.title = title;
        this.name = name;
    }
}

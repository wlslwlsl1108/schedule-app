package com.scheduleapp.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequest {

    private String title;    // 수정할 일정 제목
    private String content;  // 수정할 일정 내용
}

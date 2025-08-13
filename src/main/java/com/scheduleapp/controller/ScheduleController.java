package com.scheduleapp.controller;

import com.scheduleapp.dto.ScheduleRequest;
import com.scheduleapp.dto.ScheduleResponse;
import com.scheduleapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // CRUD의 [C] -> 일정 생성(저장)
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponse> createSchedule(
            @RequestBody ScheduleRequest scheduleRequest
    ){
        return ResponseEntity.ok(scheduleService.save(scheduleRequest));
    }

    // CRUD의 [R] -> 일정 전체 조회 및 작성자명 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponse>> getSchedules(
            @RequestBody(required = false) String name
    ){
        if(name == null){
            return ResponseEntity.ok(scheduleService.findSchedules());
        } else {
            return ResponseEntity.ok(scheduleService.findSchedulesName(name));
        }
    }


    // CRUD의 [R] -> 일정 단건 조회


    // CRUD의 [U] -> 일정 수정


    // CRUD의 [D] -> 일정 삭제
}
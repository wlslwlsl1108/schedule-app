package com.scheduleapp.controller;

import com.scheduleapp.dto.ScheduleRequest;
import com.scheduleapp.dto.ScheduleResponse;
import com.scheduleapp.dto.ScheduleUpdateRequest;
import com.scheduleapp.dto.ScheduleUpdateResponse;
import com.scheduleapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/schedules/{schedulId}")
    public ResponseEntity<ScheduleResponse> getSchedule(
            @PathVariable Long scheduleId
    ){
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }


    // CRUD의 [U] -> 일정 수정
    @PutMapping("/schedules/{schedulId}")
    public ResponseEntity<ScheduleUpdateResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequest scheduleUpdateRequest
    ){
        return ResponseEntity.ok(scheduleService.update(scheduleId, scheduleUpdateRequest));
    }


    // CRUD의 [D] -> 일정 삭제
}
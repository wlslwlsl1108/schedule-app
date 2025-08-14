package com.scheduleapp.schedule.controller;

import com.scheduleapp.schedule.dto.ScheduleRequest;
import com.scheduleapp.schedule.dto.ScheduleResponse;
import com.scheduleapp.schedule.dto.ScheduleUpdateRequest;
import com.scheduleapp.schedule.dto.ScheduleUpdateResponse;
import com.scheduleapp.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // CRUD의 [C] -> 유저의 일정 생성(저장)
    @PostMapping("/users/{userId}/schedules")
    public ResponseEntity<ScheduleResponse> saveSchedule(
            @RequestBody ScheduleRequest scheduleRequest,
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(scheduleService.save(scheduleRequest, userId));
    }

    // CRUD의 [R] -> 유저의 일정 전체 조회
    @GetMapping("/users/{userId}/schedules")
    public ResponseEntity<List<ScheduleResponse>> getAllSchedules(
           // @RequestParam(name = "name",required = false) String name
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(scheduleService.findAll(userId));
    }

    // CRUD의 [R] -> 유저의 일정 단건 조회
    @GetMapping("/users/{userId}/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponse> getSchedule(
            @PathVariable Long userId,
            @PathVariable Long scheduleId
    ){
        return ResponseEntity.ok(scheduleService.findOne(userId, scheduleId));
    }

    // CRUD의 [U] -> 유저의 일정 수정
    @PutMapping("/users/{userId}/schedules/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponse> updateSchedule(
            @PathVariable Long userId,
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequest scheduleUpdateRequest
    ){
        return ResponseEntity.ok(scheduleService.update(userId, scheduleId, scheduleUpdateRequest));
    }

    // CRUD의 [D] -> 유저의 일정 삭제
    @DeleteMapping("/users/{userId}/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long userId,
            @PathVariable Long scheduleId
    ){
        scheduleService.delete(userId, scheduleId);
        return ResponseEntity.noContent().build();
    }
}
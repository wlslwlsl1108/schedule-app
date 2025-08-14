package com.scheduleapp.schedule.controller;

import com.scheduleapp.schedule.dto.ScheduleRequest;             // 일정 생성 요청 DTO
import com.scheduleapp.schedule.dto.ScheduleResponse;            // 일정 응답 DTO
import com.scheduleapp.schedule.dto.ScheduleUpdateRequest;       // 일정 수정 요청 DTO
import com.scheduleapp.schedule.dto.ScheduleUpdateResponse;      // 일정 수정 응답 DTO
import com.scheduleapp.schedule.service.ScheduleService;         // 일정 서비스
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController                    // REST 컨트롤러
@RequiredArgsConstructor              // 서비스 주입
public class ScheduleController {

    private final ScheduleService scheduleService;           // 의존성

    // CRUD의 [C] -> 유저의 일정 생성(저장)
    @PostMapping("/users/{userId}/schedules")
    public ResponseEntity<ScheduleResponse> saveSchedule(
            @RequestBody ScheduleRequest scheduleRequest,    // 제목, 내용
            @PathVariable Long userId                        // 소유 유저 ID
    ){
        return ResponseEntity.ok(scheduleService.save(scheduleRequest, userId));
    }

    // CRUD의 [R] -> 유저의 일정 전체 조회
    @GetMapping("/users/{userId}/schedules")
    public ResponseEntity<List<ScheduleResponse>> getAllSchedules(
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
        return ResponseEntity.ok(
                scheduleService.update(
                        userId,
                        scheduleId,
                        scheduleUpdateRequest)
        );
    }

    // CRUD의 [D] -> 유저의 일정 삭제
    @DeleteMapping("/users/{userId}/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long userId,
            @PathVariable Long scheduleId
    ){
        scheduleService.delete(
                userId,
                scheduleId
        );
        return ResponseEntity.noContent().build();
    }
}
package com.scheduleapp.service;

import com.scheduleapp.dto.ScheduleRequest;
import com.scheduleapp.dto.ScheduleResponse;
import com.scheduleapp.entity.Schedule;
import com.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // CRUD의 [C] -> 일정 생성(저장)
    @Transactional
    public ScheduleResponse save(ScheduleRequest scheduleRequest) {
        Schedule savedSchedule = scheduleRepository.save(
                new Schedule(
                        scheduleRequest.getName(),
                        scheduleRequest.getTitle(),
                        scheduleRequest.getContent()
                )
        );

        return new ScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getName(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
    }

    // CRUD의 [R] -> 일정 전체 조회


    // CRUD의 [R] -> 일정 단건 조회


    // CRUD의 [U] -> 일정 수정


    // CRUD의 [D] -> 일정 삭제



}

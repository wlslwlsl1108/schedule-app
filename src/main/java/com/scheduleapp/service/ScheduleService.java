package com.scheduleapp.service;

import com.scheduleapp.dto.ScheduleRequest;
import com.scheduleapp.dto.ScheduleResponse;
import com.scheduleapp.dto.ScheduleUpdateRequest;
import com.scheduleapp.dto.ScheduleUpdateResponse;
import com.scheduleapp.entity.Schedule;
import com.scheduleapp.entity.User;
import com.scheduleapp.repository.ScheduleRepository;
import com.scheduleapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // CRUD의 [C] -> 유저의 일정 생성(저장)
    @Transactional
    public ScheduleResponse save(ScheduleRequest scheduleRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("그런 userId의 user는 없어요.")
        );
        Schedule schedule = new Schedule(
                scheduleRequest.getName(),
                scheduleRequest.getTitle(),
                scheduleRequest.getContent(),
                user
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getName(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
    }

    // CRUD의 [R] -> 유저의 일정 전체 조회
    @Transactional(readOnly = true)
    public List<ScheduleResponse> findAll(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("그런 userId의 user는 없어요.")
        );

        List<Schedule> users = scheduleRepository.findAllByUser(user);
        /* (
                Sort.by("createdAt").descending()
        );*/
        List<ScheduleResponse> dtos = new ArrayList<>();

        for (Schedule schedule : users) {
            dtos.add(
                    new ScheduleResponse(
                            schedule.getId(),
                            schedule.getName(),
                            schedule.getTitle(),
                            schedule.getContent(),
                            schedule.getCreatedAt(),
                            schedule.getUpdatedAt()
                    )
            );
        }
        return dtos;
    }

    /*
    // CRUD - "R (Read)"  => 작성자명으로 조회
    public List<ScheduleResponse> findSchedulesName(String name) {
        List<Schedule> schedules = scheduleRepository.findAllByUser(name, Sort.by("createdAt").descending());

        List<ScheduleResponse> dtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
            ScheduleResponse scheduleResponse = new ScheduleResponse(
                    schedule.getId(),
                    schedule.getName(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt()
            );
            dtos.add(scheduleResponse);
        }
        return dtos;
    }
    */

    // CRUD의 [R] -> 유저의 일정 단건 조회
    @Transactional(readOnly = true)
    public ScheduleResponse findOne(Long userId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findByUser_IdAndId(userId, scheduleId).orElseThrow(
                () -> new IllegalArgumentException("그런 userId의 user는 없어요.")
        );

        return new ScheduleResponse(
                schedule.getId(),
                schedule.getName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }


    // CRUD의 [U] -> 유저의 일정 수정
    @Transactional
    public ScheduleUpdateResponse update(Long userId, Long scheduleId, ScheduleUpdateRequest scheduleUpdateRequest) {
        Schedule schedule = scheduleRepository.findByUser_IdAndId(userId, scheduleId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 scheduleId가 없습니다.")
        );
        /* schedule.updateContent(
                scheduleUpdateRequest.getTitle(),
                scheduleUpdateRequest.getName()
                ); */
        schedule.updateContent(
                scheduleUpdateRequest.getName(),
                scheduleUpdateRequest.getTitle()
        );

        return new ScheduleUpdateResponse(
                schedule.getId(),
                schedule.getName(),
                schedule.getTitle()
        );
    }


    // CRUD의 [D] -> 유저의 일정 삭제
    @Transactional
    public void delete(Long userId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findByUser_IdAndId(userId, scheduleId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 scheduleId가 없습니다.")
        );
        scheduleRepository.delete(schedule);
    }



}

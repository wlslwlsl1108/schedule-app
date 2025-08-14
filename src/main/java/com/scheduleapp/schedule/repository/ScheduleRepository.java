package com.scheduleapp.schedule.repository;

import com.scheduleapp.schedule.entity.Schedule;
import com.scheduleapp.schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // 특정 유저의 모든 일정 //
    List<Schedule> findAllByUser(User user);

    // 특정 유저 소유 일정 전체 삭제 //
    void deleteByUser(User user);

    // 소유자 + ID //
    Optional<Schedule> findByUser_IdAndId(Long userId, Long scheduleId);
}

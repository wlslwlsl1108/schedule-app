package com.scheduleapp.schedule.repository;

import com.scheduleapp.schedule.entity.Schedule;
import com.scheduleapp.schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByUser(User user);

    void deleteByUser(User user);

    Optional<Schedule> findByUser_IdAndId(Long userId, Long scheduleId);
}

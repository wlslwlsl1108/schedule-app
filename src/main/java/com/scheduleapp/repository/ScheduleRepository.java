package com.scheduleapp.repository;

import com.scheduleapp.entity.Schedule;
import com.scheduleapp.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByName(String name, Sort sort);

    void deleteByUser(User user);

    Optional<Schedule> findByUser_IdAndId(Long UserId, Long ScheduleId);
}

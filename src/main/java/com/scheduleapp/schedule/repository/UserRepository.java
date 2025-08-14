package com.scheduleapp.schedule.repository;

import com.scheduleapp.schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 단건 조회 //
    Optional<User> findByEmail(String email);
}

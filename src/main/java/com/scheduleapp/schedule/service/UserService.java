package com.scheduleapp.schedule.service;

import com.scheduleapp.auth.dto.AuthRequest;
import com.scheduleapp.schedule.dto.UserResponse;
import com.scheduleapp.schedule.entity.User;
import com.scheduleapp.schedule.repository.ScheduleRepository;
import com.scheduleapp.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    // 레포지토리 의존성
    private final UserRepository userRepository;            // 유저 저장소
    private final ScheduleRepository scheduleRepository;    // 일정 저장소

    // CRUD의 [C] -> 유저 생성(저장)은 auth에서 진행되므로 삭제
    // CRUD의 [R] -> 유저 전체 조회
    @Transactional
    public List<UserResponse> findAll() {                   // 모든 유저 조회

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(m -> new UserResponse(            // 엔티티 -> DTO 변환
                        m.getId(),
                        m.getName(),
                        m.getEmail(),
                        m.getCreatedAt(),
                        m.getUpdatedAt()))
                .toList();
    }


    // CRUD의 [R] -> 유저 단건 조회
    @Transactional(readOnly = true)
    public UserResponse findById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 userId가 없습니다.")
        );
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }


    // CRUD의 [U] -> 유저 수정
    @Transactional
    public UserResponse update(Long userId, AuthRequest authRequest/*UserRequest userRequest*/) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 userId가 없습니다.")
        );
        user.updateEmail(authRequest.getEmail());

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }


    // CRUD의 [D] -> 유저 삭제
    @Transactional
    public void delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 userId가 없습니다.")
        );
        scheduleRepository.deleteByUser(user);
        userRepository.delete(user);
    }
}

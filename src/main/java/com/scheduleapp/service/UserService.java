package com.scheduleapp.service;

import com.scheduleapp.dto.ScheduleResponse;
import com.scheduleapp.dto.UserRequest;
import com.scheduleapp.dto.UserResponse;
import com.scheduleapp.entity.User;
import com.scheduleapp.repository.ScheduleRepository;
import com.scheduleapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    // 레포지토리 의존성
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // CRUD의 [C] -> 유저 생성(저장)
    @Transactional
    public UserResponse save(UserRequest request) {
        User user = new User(request.getName(), request.getEmail());
        User savedUser = userRepository.save(user);
        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
    }

    // CRUD의 [R] -> 유저 전체 조회
    @Transactional
    public List<UserResponse> findAll() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(m -> new UserResponse(m.getId(), m.getName(), m.getEmail()))
                .toList();
    }


    // CRUD의 [R] -> 유저 단건 조회
    @Transactional(readOnly = true)
    public UserResponse findById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 userId가 없습니다.")
        );
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }


    // CRUD의 [U] -> 유저 수정
    @Transactional
    public UserResponse update(Long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 userId가 없습니다.")
        );
        user.updateEmail(userRequest.getEmail());

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }


    // CRUD의 [D] -> 유저 삭제




}

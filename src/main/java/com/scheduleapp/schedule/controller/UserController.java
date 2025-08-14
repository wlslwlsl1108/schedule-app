package com.scheduleapp.schedule.controller;

import com.scheduleapp.auth.dto.AuthRequest;               // 이메일, 비밀번호  -> 여기서는 이메일만 사용
import com.scheduleapp.schedule.dto.UserResponse;
import com.scheduleapp.schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // CRUD의 [C] 는 auth에서 처리되므로 삭제
    // CRUD의 [R] -> 유저 전체 조회
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    // CRUD의 [R] -> 유저 단건 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUser(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    // CRUD의 [U] -> 유저 수정
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @RequestBody AuthRequest authRequest,      // 이메일만 사용 (비밀번호는 무시)
            @PathVariable("userId") Long userId
    ) {
        return ResponseEntity.ok(userService.update(userId, authRequest));
    }

    // CRUD의 [D] -> 유저 삭제
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long userId
    ) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}

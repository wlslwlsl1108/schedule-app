package com.scheduleapp.controller;

import com.scheduleapp.dto.UserRequest;
import com.scheduleapp.dto.UserResponse;
import com.scheduleapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // CRUD의 [C] -> 유저 생성(저장)
    @PostMapping("/users")
    public ResponseEntity<UserResponse> saveUser(
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(userService.save(request));
    }

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
            @RequestBody UserRequest userrequest,
            @PathVariable("userId") Long userId
    ) {
        return ResponseEntity.ok(userService.update(userId, userrequest));
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

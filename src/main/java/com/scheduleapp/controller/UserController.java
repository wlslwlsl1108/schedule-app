package com.scheduleapp.controller;

import com.scheduleapp.dto.UserRequest;
import com.scheduleapp.dto.UserResponse;
import com.scheduleapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // CRUD의 [C] -> 영화 생성(저장)
    @PostMapping("/users")
    public ResponseEntity<UserResponse> saveUser(
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(userService.save(request));
    }


    // CRUD의 [R] -> 유저 전체 조회


    // CRUD의 [R] -> 유저 단건 조회


    // CRUD의 [U] -> 유저 수정


    // CRUD의 [D] -> 유저 삭제



}

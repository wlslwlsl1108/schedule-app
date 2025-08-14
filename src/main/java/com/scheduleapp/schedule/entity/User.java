package com.scheduleapp.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)           // 자동 키 값 증가
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)                      // 이메일 유니크 + NOT NULL
    private String email;
    private String password;

    public User(String name, String email, String password) {     // 회원가입 용 생성자
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void updateEmail(String email) {                       // 이메일 변경 생성자
        this.email = email;
    }
}

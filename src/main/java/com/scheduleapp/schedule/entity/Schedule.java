package com.scheduleapp.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {
// -> 상속 받았으므로 createdAt, updatedAt 밑에 적어주지 않아도 사용 가능

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)        // 자동 키 값 증가
    private Long id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)                         // 연관관계 주입 -> [다(여러일정) : 1(1명 유저)]
    @JoinColumn(name = "user_id", nullable = false)            // FK 컬럼명 + NOT NULL
    private User user;

    public Schedule(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void updateContent(String title, String content){   // 일정 수정 생성자
        this.title = title;
        this.content = content;
    }

}

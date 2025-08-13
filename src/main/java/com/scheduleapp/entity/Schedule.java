package com.scheduleapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {  // 상속 받았으므로 createdAt, updatedAt 밑에 적어주지 않아도 사용 가능

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Schedule(String name, String title, String content, User user) {
        this.name = name;
        this.title = title;
        this.content = content;
        this.user = user;
    }

        public void updateContent(String title, String name){
        this.title = title;
        this.name = name;
    }

}

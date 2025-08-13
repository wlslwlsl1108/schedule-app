package com.scheduleapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public Schedule(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
    }

    /*  수정 작성시 주석 풀기
        public void updateContent(String title, String name){
        this.title = title;
        this.name = name;
    }*/

}

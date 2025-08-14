package com.scheduleapp.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass                                  // 상속받는 엔터티에 컬럼만 전달
@EntityListeners(AuditingEntityListener.class)     // JPA Auditing 활성화
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)                     // 생성 시에만 값 설정
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;
}

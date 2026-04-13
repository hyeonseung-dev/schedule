package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Schedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    private String content;
    @Column(length = 8, nullable = false)
    private String authorName;
    @Column(length = 8, nullable = false)
    private String password;
    // 댓글 수 관리
    private int commentCount = 0;

    public Schedule(String title, String content, String authorName, String password){
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.password = password;
    }

    // 일정 수정 시 일정 제목과 작성자 명만 수정할 수 있게 만듦, 수정일 업데이트 시점으로 변경
    public void update(String title, String authorName){
        this.title = title;
        this.authorName = authorName;

        // BaseEntity에 있는 수정일 메서드 사용
        updateModifiedAt();
    }

    // 댓글 수 증가시키는 기능
    public void commentCountIncrease(){
        commentCount++;
    }
}

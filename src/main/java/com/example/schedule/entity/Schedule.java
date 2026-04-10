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
    private String title;
    private String content;
    private String authorName;
    private String password;

    public Schedule(String title, String content, String authorName, String password){
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.password = password;
    }

    // 일정 수정 시 일정 제목과 작성자 명만 수정할 수 있게 만듦
    public void update(String title, String authorName){
        this.title = title;
        this.authorName = authorName;
    }

}

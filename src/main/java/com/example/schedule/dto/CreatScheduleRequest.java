package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class CreatScheduleRequest {
    private String title;
    private String content;
    private String authorName;
    private String password;

    public void validate(){
        if(title.length() > 30){
            throw new IllegalArgumentException("제목은 30자를 초과할 수 없습니다.");
        }

        if(content.length() > 200){
            throw new IllegalArgumentException("내용은 200자를 초과할 수 없습니다.");
        }

        if(authorName == null){
            throw new IllegalArgumentException("작성자명은 필수로 입력해야합니다.");
        }

        if(password == null){
            throw new IllegalArgumentException("비밀번호는 필수로 입력해야합니다.");
        }

    }
}

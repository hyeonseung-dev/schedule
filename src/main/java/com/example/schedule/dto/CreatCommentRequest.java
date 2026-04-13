package com.example.schedule.dto;

import lombok.Getter;

@Getter

public class CreatCommentRequest {
    private Long scheduleid;
    private String content;
    private String authorName;
    private String password;

    public void validate(){
        if(content.length() > 100){
            throw new IllegalArgumentException("댓글 내용은 100자를 초과할 수 없습니다.");
        }
        if(authorName == null){
            throw new IllegalArgumentException("작성자명은 필수로 입력해야합니다.");
        }

        if(password == null){
            throw new IllegalArgumentException("비밀번호는 필수로 입력해야합니다.");
        }

        if(authorName.length() > 8){
            throw new IllegalArgumentException("작성자명은 8자를 초과할수 없습니다.");
        }

        if(password.length() > 8){
            throw new IllegalArgumentException("비밀번호는 8자를 초과할수 없습니다.");
        }

    }
}

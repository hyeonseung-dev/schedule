package com.example.schedule.dto;

import lombok.Getter;

@Getter

public class CreatCommentRequest {
    private Long scheduleid;
    private String content;
    private String authorName;
    private String password;
}

package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class CreatScheduleRequest {
    private String title;
    private String content;
    private String authorName;
    private String password;
}

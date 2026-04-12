package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class PatchScheduleResponse {
    private final Long id;
    private final String title;
    private final String authorName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public PatchScheduleResponse(Long id, String title, String authorName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}

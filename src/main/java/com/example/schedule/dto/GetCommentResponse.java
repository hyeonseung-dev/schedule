package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class GetCommentResponse {
    private final String content;
    private final String authorName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetCommentResponse(String content, String authorName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.content = content;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}

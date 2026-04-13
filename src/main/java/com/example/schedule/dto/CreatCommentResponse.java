package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class CreatCommentResponse {
    private final Long commentid;
    private final Long scheduleid;
    private final String content;
    private final String authorName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreatCommentResponse(Long commentid, Long scheduleid, String content, String authorName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.commentid = commentid;
        this.scheduleid = scheduleid;
        this.content = content;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}

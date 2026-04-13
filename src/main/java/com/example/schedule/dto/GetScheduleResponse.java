package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetScheduleResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String authorName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<GetCommentResponse> getCommentResponseslist;


    public GetScheduleResponse(Long id, String title, String content, String authorName, LocalDateTime createdAt, LocalDateTime modifiedAt, List<GetCommentResponse> getCommentResponseslist) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.getCommentResponseslist = getCommentResponseslist;
    }
}

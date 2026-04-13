package com.example.schedule.controller;

import com.example.schedule.dto.*;
import com.example.schedule.service.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@RequiredArgsConstructor
@RestController

public class ScheduleController {
    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("/schedules")
    public ResponseEntity<CreatScheduleResponse> creat(@RequestBody CreatScheduleRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    // 선택 일정 조회
    @GetMapping("/schedules/{id}")
    public ResponseEntity<GetScheduleCommentResponse> getSchedule(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOneSchedule(id));
    }

    // 전체 일정 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedule(@RequestParam(required = false) String authorName){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAllSchedule(authorName));
    }

    // 일정 수정
    @PatchMapping("/schedules/{id}")
    public ResponseEntity<UpdateScheduleResponse> patchSchedule(@PathVariable Long id, @RequestBody UpdateScheduleRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(id,request));
    }

    // 일정 삭제
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Void> deletSchedule(@PathVariable Long id){
        scheduleService.deleteSchedule(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 댓글 생성
    @PostMapping("/comments")
    public ResponseEntity<CreatCommentResponse> creat(@RequestBody CreatCommentRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.commentSave(request));
    }
}

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
    public ResponseEntity<?> creat(@RequestBody CreatScheduleRequest request) {
        try {
            request.validate();
            return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 선택 일정 조회
    @GetMapping("/schedules/{id}")
    public ResponseEntity<?> getSchedule(@PathVariable Long id){
        try {
            scheduleService.getOneSchedule(id);
            return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOneSchedule(id));
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 전체 일정 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedule(@RequestParam(required = false) String authorName){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAllSchedule(authorName));
    }

    // 일정 수정
    @PatchMapping("/schedules/{id}")
    public ResponseEntity<?> patchSchedule(@PathVariable Long id, @RequestBody UpdateScheduleRequest request){
        try {
            scheduleService.updateSchedule(id,request);
            return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(id,request));
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 일정 삭제
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id,@RequestBody DeleteScheduleRequest request){
        try {
            scheduleService.deleteSchedule(id,request);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 댓글 생성
    @PostMapping("/comments")
    public ResponseEntity<?> creat(@RequestBody CreatCommentRequest request){
        try {
            request.validate();
            return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.commentSave(request));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

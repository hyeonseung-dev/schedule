package com.example.schedule.controller;

import com.example.schedule.dto.CreatScheduleRequest;
import com.example.schedule.dto.CreatScheduleResponse;
import com.example.schedule.dto.GetScheduleResponse;
import com.example.schedule.service.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<GetScheduleResponse> getSchedule(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOneSchedule(id));
    }

    // 전체 일정 조회
}

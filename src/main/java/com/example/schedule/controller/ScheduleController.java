package com.example.schedule.controller;

import com.example.schedule.dto.CreatScheduleRequest;
import com.example.schedule.dto.CreatScheduleResponse;
import com.example.schedule.service.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RequiredArgsConstructor
@RestController

public class ScheduleController {
    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("schedules")
    public ResponseEntity<CreatScheduleResponse> creat(@RequestBody CreatScheduleRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }
}

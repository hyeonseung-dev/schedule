package com.example.schedule.service;

import com.example.schedule.dto.CreatScheduleRequest;
import com.example.schedule.dto.CreatScheduleResponse;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Getter
@RequiredArgsConstructor
@Service

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;


    // 저장
    @Transactional
    public CreatScheduleResponse save(CreatScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getAuthorName(),
                request.getPassword()
                );

        scheduleRepository.save(schedule);

        return new CreatScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthorName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }
}

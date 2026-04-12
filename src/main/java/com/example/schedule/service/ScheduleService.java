package com.example.schedule.service;

import com.example.schedule.dto.CreatScheduleRequest;
import com.example.schedule.dto.CreatScheduleResponse;
import com.example.schedule.dto.GetScheduleResponse;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    // 선택 조회
    @Transactional(readOnly = true)
    public GetScheduleResponse getOneSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("일정이 없습니다.")
        );
        return new GetScheduleResponse(schedule.getId(),schedule.getTitle(),schedule.getContent(),schedule.getAuthorName(),schedule.getCreatedAt(),schedule.getModifiedAt());
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedule(String authorName) {
        // 작성자 조건 없을 때 전체조회
        if(authorName == null){
            List<GetScheduleResponse> dtos = new ArrayList<>();

            for(Schedule schedule : scheduleRepository.findAll()){
                GetScheduleResponse dto = new GetScheduleResponse(schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getAuthorName(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt());
                dtos.add(dto);
            }

            //수정일 기준으로 내림차순 정렬
            dtos.sort(Comparator.comparing(GetScheduleResponse::getModifiedAt).reversed());
            return dtos;

        }else
        // 작성자 조건 있을 때 전체조회
        {
            List<GetScheduleResponse> dtos = new ArrayList<>();

            for(Schedule schedule : scheduleRepository.findAll()){
                if(authorName.equals(schedule.getAuthorName())) {
                    GetScheduleResponse dto = new GetScheduleResponse(schedule.getId(),
                            schedule.getTitle(),
                            schedule.getContent(),
                            schedule.getAuthorName(),
                            schedule.getCreatedAt(),
                            schedule.getModifiedAt());
                    dtos.add(dto);
                }
            }
            //수정일 기준으로 내림차순 정렬
            dtos.sort(Comparator.comparing(GetScheduleResponse::getModifiedAt).reversed());
            return dtos;
        }
    }
}

package com.example.schedule.service;

import com.example.schedule.dto.*;
import com.example.schedule.entity.Comment;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.CommentRepository;
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
    private final CommentRepository commentRepository;


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

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long id, UpdateScheduleRequest request) {

        // 요청한 id가 존재여부 확인, 없으면 예외메세지 전달
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("일정이 없습니다.")
        );

        // 요청한 비밀번호와 기존 생성된 일정 비밀번호와 같으면 수정
        if(request.getPassword().equals(schedule.getPassword())){
            schedule.update(request.getTitle(), request.getAuthorName());
        }
        else
        // 요청한 비밀번호와 기존 생성된 일정 비밀번호와 다르면 예외처리
        {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getAuthorName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public void deleteSchedule(Long id) {
        // 대상 id가 존재하지 않을 때 예외처리
        boolean exist = scheduleRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("대상 id가 존재하지 않습니다.");
        }
        else
        // 대상 id 존재 시 삭제
        {
            scheduleRepository.deleteById(id);
        }

    }

    // 댓글 생성
    @Transactional
    public CreatCommentResponse commentSave(CreatCommentRequest request) {
        Comment comment = new Comment(
                request.getScheduleid(),
                request.getContent(),
                request.getAuthorName(),
                request.getAuthorName());

        commentRepository.save(comment);

        return new CreatCommentResponse(
                comment.getCommentid(),
                comment.getScheduleid(),
                comment.getContent(),
                comment.getAuthorName(),
                comment.getCreatedAt(),
                comment.getModifiedAt());
    }
}

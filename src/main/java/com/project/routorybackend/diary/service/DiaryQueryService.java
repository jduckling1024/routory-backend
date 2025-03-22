package com.project.routorybackend.diary.service;

import com.project.routorybackend.diary.model.Diary;
import com.project.routorybackend.diary.repository.DiaryRepository;
import com.project.routorybackend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryQueryService {
    private final DiaryRepository diaryRepository;

    public Diary readOne(String id) {
        return diaryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("해당 일기가 존재하지 않습니다."));
    }
}

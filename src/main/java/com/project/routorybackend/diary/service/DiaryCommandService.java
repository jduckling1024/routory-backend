package com.project.routorybackend.diary.service;

import com.project.routorybackend.diary.model.Diary;
import com.project.routorybackend.diary.repository.DiaryRepository;
import com.project.routorybackend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryCommandService {
    private final DiaryRepository diaryRepository;

    public String save(Diary diary) {
        final Diary saved = diaryRepository.save(diary);

        return saved.getId();
    }

    public void update(Diary diary) {
        Optional.ofNullable(findOne(diary.getId())).orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 diary 입니다."));

        diaryRepository.update(diary);
    }

    public Diary findOne(String id) {
        return diaryRepository.findById(id).orElse(null);
    }
}

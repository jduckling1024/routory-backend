package com.project.routorybackend.diary.service;

import com.project.routorybackend.diary.dto.CreateDiaryRequest;
import com.project.routorybackend.diary.dto.ReadOneDiaryResponse;
import com.project.routorybackend.diary.dto.UpdateDiaryRequest;
import com.project.routorybackend.diary.model.Diary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryApiService {
    private final DiaryCommandService diaryCommandService;
    private final DiaryQueryService diaryQueryService;

    public String save(CreateDiaryRequest request) {
        return diaryCommandService.save(request.toDiary());
    }

    public ReadOneDiaryResponse readOne(String id) {
        final Diary diary = diaryQueryService.readOne(id);

        return ReadOneDiaryResponse.from(diary);
    }

    public void update(String id, UpdateDiaryRequest request) {
        diaryCommandService.update(request.toDiary(id));
    }
}

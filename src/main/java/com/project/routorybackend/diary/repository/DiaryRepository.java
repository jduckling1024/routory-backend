package com.project.routorybackend.diary.repository;

import com.project.routorybackend.diary.model.Diary;

import java.util.Optional;

public interface DiaryRepository {
    Diary save(Diary diary);
    void update(Diary diary);
    Optional<Diary> findById(String id);
}

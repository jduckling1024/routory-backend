package com.project.routorybackend.diary.dto;

import com.project.routorybackend.diary.model.Diary;
import com.project.routorybackend.diary.model.Emotion;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.project.routorybackend.diary.model.Diary}
 */
public record ReadOneDiaryResponse(String id, String content, Emotion emotion, LocalDateTime createdDate, LocalDateTime updatedDate) implements Serializable {
    public static ReadOneDiaryResponse from(Diary diary) {
        return new ReadOneDiaryResponse(diary.getId(), diary.getContent(), diary.getEmotion(), diary.getCreatedDate(), diary.getUpdatedDate());
    }
}

package com.project.routorybackend.diary.dto;

import com.project.routorybackend.diary.model.Diary;
import com.project.routorybackend.diary.model.Emotion;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link Diary}
 */
public record UpdateDiaryRequest(@NotBlank String content,
                                 String emotion) implements Serializable {
    public Diary toDiary(String id) {
        return Diary.builder()
                .id(id)
                .content(content)
                .emotion(Emotion.valueOf(emotion))
                .build();
    }
}
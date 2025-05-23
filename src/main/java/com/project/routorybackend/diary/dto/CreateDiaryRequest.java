package com.project.routorybackend.diary.dto;

import com.project.routorybackend.account.model.Account;
import com.project.routorybackend.diary.model.Diary;
import com.project.routorybackend.diary.model.Emotion;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.project.routorybackend.diary.model.Diary}
 */
public record CreateDiaryRequest(@NotBlank(message = "내용이 비어있습니다.") String content,
                                 String emotion) implements Serializable {
    public Diary toDiary(Account account) {
        return Diary.builder()
                .content(content)
                .emotion(Emotion.valueOfWithDefault(emotion))
                .createdDate(LocalDateTime.now())
                .account(account)
                .build();
    }
}
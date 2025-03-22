package com.project.routorybackend.diary.dto;

import com.project.routorybackend.diary.model.Diary;

import java.io.Serializable;

/**
 * DTO for {@link Diary}
 */
public record CreateDiaryResponse(String id) implements Serializable {
}
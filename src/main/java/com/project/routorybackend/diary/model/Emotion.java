package com.project.routorybackend.diary.model;

import java.util.Arrays;
import java.util.Set;

public enum Emotion {
    HAPPY, // 행복
    SAD, // 슬픔
    ANGRY, // 분노
    CALM, // 평온
    TIRED, // 피곤
    UNDEFINED; // 정의되지 않은 감정

    private static final Emotion DEFAULT = UNDEFINED;

    private static final Set<String> EMOTION_NAME_SET = Set.of(Arrays.stream(values()).map(Emotion::name).toArray(String[]::new));

    public static Emotion valueOfWithDefault(String emotion) {
        if (emotion == null) return DEFAULT;

        return EMOTION_NAME_SET.contains(emotion) ? Emotion.valueOf(emotion) : DEFAULT;
    }
}

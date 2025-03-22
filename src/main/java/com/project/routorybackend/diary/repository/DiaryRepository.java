package com.project.routorybackend.diary.repository;

import com.project.routorybackend.diary.model.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiaryRepository extends MongoRepository<Diary, String>, CustomDiaryRepository {
}

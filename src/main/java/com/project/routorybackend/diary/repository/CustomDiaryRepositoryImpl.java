package com.project.routorybackend.diary.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.routorybackend.diary.model.Diary;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
class CustomDiaryRepositoryImpl implements CustomDiaryRepository {
    private final ObjectMapper objectMapper;
    private final MongoTemplate mongoTemplate;

    // TODO: 공통으로 빼기
    @Override
    public void update(Diary diary) {
        final Query query = new Query(Criteria.where("_id").is(diary.getId()));
        final Update update = new Update();

        Map<String, Object> fields = objectMapper.convertValue(diary, new TypeReference<Map<String, Object>>() {}).entrySet().stream().
                filter(it -> it.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        fields.forEach(update::set);

        // update date
        update.set("updatedDate", LocalDateTime.now());

        mongoTemplate.updateFirst(query, update, Diary.class);
    }
}

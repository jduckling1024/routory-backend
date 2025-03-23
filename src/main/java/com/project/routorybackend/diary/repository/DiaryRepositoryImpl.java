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

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
class DiaryRepositoryImpl implements DiaryRepository {
    private final ObjectMapper objectMapper;
    private final MongoTemplate mongoTemplate;

    @Override
    public Diary save(Diary diary) {
        return mongoTemplate.save(diary);
    }

    // TODO: 공통으로 빼기
    @Override
    public void update(Diary diary) {
        final Query query = new Query(Criteria.where("_id").is(diary.getId()));
        final Update update = new Update();

        Map<String, Object> fields = objectMapper.convertValue(diary, new TypeReference<Map<String, Object>>() {}).entrySet().stream().
                filter(it -> it.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        fields.forEach(update::set);

        mongoTemplate.update(Diary.class).matching(query).apply(update).all();
    }

    @Override
    public Optional<Diary> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, Diary.class));
    }
}

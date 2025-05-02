package com.project.routorybackend.token.repository;

import com.project.routorybackend.token.entity.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<Token> findByEmail(String email) {
        return Optional.ofNullable(mongoTemplate.findById(email, Token.class));
    }

    @Override
    public void save(Token token) {
        mongoTemplate.save(token);
    }
}

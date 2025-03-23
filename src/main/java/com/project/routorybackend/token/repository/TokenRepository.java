package com.project.routorybackend.token.repository;

import com.project.routorybackend.token.entity.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<Token, Long> {
    Optional<Token> findByEmail(String email);
}

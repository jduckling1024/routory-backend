package com.project.routorybackend.token.repository;

import com.project.routorybackend.token.entity.Token;

import java.util.Optional;

public interface TokenRepository {
    Optional<Token> findByEmail(String email);

    void save(Token token);
}

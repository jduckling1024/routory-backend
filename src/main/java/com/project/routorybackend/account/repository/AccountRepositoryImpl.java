package com.project.routorybackend.account.repository;

import com.project.routorybackend.account.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<Account> findByEmail(String email) {
        return Optional.ofNullable(mongoTemplate.findById(email, Account.class));
    }

    @Override
    public Account save(Account account) {
        return mongoTemplate.save(account);
    }
}

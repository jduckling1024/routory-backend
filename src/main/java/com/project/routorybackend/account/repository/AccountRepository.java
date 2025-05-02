package com.project.routorybackend.account.repository;

import com.project.routorybackend.account.model.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findByEmail(String email);

    Account save(Account account);
}

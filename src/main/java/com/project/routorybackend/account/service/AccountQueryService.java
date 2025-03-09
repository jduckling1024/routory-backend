package com.project.routorybackend.account.service;

import com.project.routorybackend.account.model.Account;
import com.project.routorybackend.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountQueryService {
    private final AccountRepository accountRepository;

    public Optional<Account> findByIdentification(String email) {
        return accountRepository.findByEmail(email);
    }
}

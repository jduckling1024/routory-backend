package com.project.routorybackend.account.service;

import com.project.routorybackend.account.model.Account;
import com.project.routorybackend.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCommandService {
    private final AccountRepository accountRepository;

    public Account save(Account account) {
        return accountRepository.save(account);
    }
}

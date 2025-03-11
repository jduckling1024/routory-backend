package com.project.routorybackend.account.service;

import com.project.routorybackend.account.dto.SignUpInput;
import com.project.routorybackend.account.dto.SignUpResult;
import com.project.routorybackend.account.model.Account;
import com.project.routorybackend.exception.ResourceAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountApiService {
    private final AccountCommandService accountCommandService;
    private final AccountQueryService accountQueryService;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpResult signUp(SignUpInput input) {
        checkEmailDuplication(input.getEmail());
        encodePassword(input);

        final Account account = input.toAccount();
        accountCommandService.save(account);

        return SignUpResult.from(account);
    }

    private void checkEmailDuplication(String email) {
        if (accountQueryService.findByIdentification(email).isPresent()) {
            throw new ResourceAlreadyExistsException("이미 존재하는 회원입니다.");
        }
    }

    private void encodePassword(SignUpInput input) {
        input.setPassword(passwordEncoder.encode(input.getPassword()));
    }
}

package com.project.routorybackend.account.service;

import com.project.routorybackend.account.dto.SignUpRequest;
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
    public SignUpResult signUp(SignUpRequest request) {
        checkEmailDuplication(request.getEmail());
        encodePassword(request);

        final Account account = request.toAccount();
        accountCommandService.save(account);

        return SignUpResult.from(account);
    }

    private void checkEmailDuplication(String email) {
        if (accountQueryService.findByEmail(email).isPresent()) {
            throw new ResourceAlreadyExistsException("이미 존재하는 회원입니다.");
        }
    }

    private void encodePassword(SignUpRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
    }
}

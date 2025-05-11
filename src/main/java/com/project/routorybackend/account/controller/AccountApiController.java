package com.project.routorybackend.account.controller;

import com.project.routorybackend.account.dto.SignUpRequest;
import com.project.routorybackend.account.dto.SignUpResponse;
import com.project.routorybackend.account.dto.SignUpResult;
import com.project.routorybackend.account.service.AccountApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountApiController {
    private final AccountApiService accountApiService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest request) {
        final SignUpResult result = accountApiService.signUp(request);
        return new ResponseEntity<>(SignUpResponse.from(result), HttpStatus.CREATED);
    }
}

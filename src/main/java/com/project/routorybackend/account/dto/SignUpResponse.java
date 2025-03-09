package com.project.routorybackend.account.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpResponse {
    private String identification;
    private String name;

    public static SignUpResponse from(SignUpResult result) {
        return new SignUpResponse(result.getEmail(), result.getName());
    }
}

package com.project.routorybackend.account.dto;

import com.project.routorybackend.account.model.Account;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class SignUpRequest {
    @NotBlank(message = "빈문자열, 공백만 입력할 수 없습니다.")
    private String email;
    @NotBlank(message = "빈문자열, 공백만 입력할 수 없습니다.")
    private String password;
    @NotBlank(message = "빈문자열, 공백만 입력할 수 없습니다.")
    private String name;

    public Account toAccount() {
        return Account.builder()
                .email(email)
                .password(password)
                .name(name)
                .createdDate(LocalDateTime.now())
                .build();
    }
}
package com.project.routorybackend.account.dto;

import com.project.routorybackend.account.model.Account;
import lombok.Data;

@Data
public class SignUpInput {
    private String email;
    private String password;
    private String name;

    public Account toAccount() {
        return Account.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }
}

package com.project.routorybackend.account.dto;

import com.project.routorybackend.account.model.Account;
import lombok.*;

@Data
public class SignUpResult {
    private String email;
    private String name;

    public static SignUpResult from(Account account) {
        SignUpResult result = new SignUpResult();
        result.setEmail(account.getEmail());
        result.setName(account.getName());

        return result;
    }
}

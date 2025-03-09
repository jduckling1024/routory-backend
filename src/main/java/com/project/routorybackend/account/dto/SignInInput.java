package com.project.routorybackend.account.dto;

import lombok.Data;

@Data
public class SignInInput {
    private String identification;
    private String password;
}

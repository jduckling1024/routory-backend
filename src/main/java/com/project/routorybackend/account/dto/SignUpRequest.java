package com.project.routorybackend.account.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class SignUpRequest {
    @NotBlank(message = "빈문자열, 공백만 입력할 수 없습니다.")
    private String email;
    @NotBlank(message = "빈문자열, 공백만 입력할 수 없습니다.")
    private String password;
    @NotBlank(message = "빈문자열, 공백만 입력할 수 없습니다.")
    private String name;

    public SignUpInput toInput() {
        SignUpInput signUpInput = new SignUpInput();
        signUpInput.setEmail(this.email);
        signUpInput.setPassword(this.password);
        signUpInput.setName(this.name);

        return signUpInput;
    }
}
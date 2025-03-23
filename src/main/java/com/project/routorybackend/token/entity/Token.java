package com.project.routorybackend.token.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@AllArgsConstructor
@Document(collation = "token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {
    @Id
    private String id;

    private String email;

    private String refreshToken;
}

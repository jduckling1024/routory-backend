package com.project.routorybackend.account.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Builder
@Document(collection = "account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {
    @Id
    private String id;
    private String email;
    private String password;
    private String name;
    private String introduction;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
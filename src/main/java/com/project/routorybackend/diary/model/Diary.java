package com.project.routorybackend.diary.model;

import com.project.routorybackend.account.model.Account;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Builder
@Document(collection = "diary")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Diary {
    @Id
    private String id;
    private String content;
    private Emotion emotion;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    @Field("account_id")
    @DocumentReference
    private Account account;
}

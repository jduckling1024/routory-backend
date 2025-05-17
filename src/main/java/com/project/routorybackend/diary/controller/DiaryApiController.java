package com.project.routorybackend.diary.controller;

import com.project.routorybackend.account.model.Account;
import com.project.routorybackend.diary.dto.*;
import com.project.routorybackend.diary.service.DiaryApiService;
import com.project.routorybackend.security.CustomAuthenticationPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/diary")
@RestController
@RequiredArgsConstructor
public class DiaryApiController {
    private final DiaryApiService diaryApiService;

    @PostMapping
    public ResponseEntity<CreateDiaryResponse> save(
            @CustomAuthenticationPrincipal Account account,
            @Valid @RequestBody CreateDiaryRequest request) {
        final String id = diaryApiService.save(request, account);
        return ResponseEntity.ok(new CreateDiaryResponse(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadOneDiaryResponse> readOne(@PathVariable("id") String id) {
        return ResponseEntity.ok(diaryApiService.readOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateDiaryResponse> update(@PathVariable("id") String id, @Valid @RequestBody UpdateDiaryRequest request) {
        diaryApiService.update(id, request);

        return ResponseEntity.ok(new UpdateDiaryResponse(id));
    }
}

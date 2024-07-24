package com.yangyag.msa.auth.controller;

import com.yangyag.msa.auth.model.dto.UserCreateRequest;
import com.yangyag.msa.auth.model.entity.User;
import com.yangyag.msa.auth.service.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserCommandService userCommandService;

    @PostMapping
    ResponseEntity<User> createUser(@RequestBody UserCreateRequest request) {
        var result = userCommandService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}

package com.yangyag.msa.auth.controller;

import com.yangyag.msa.auth.model.dto.UserCreateRequest;
import com.yangyag.msa.auth.model.dto.UserDeleteRequest;
import com.yangyag.msa.auth.model.dto.UserUpdateRequest;
import com.yangyag.msa.auth.model.entity.User;
import com.yangyag.msa.auth.service.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserCommandService userCommandService;

    @PostMapping
    ResponseEntity<User> createUser(@RequestBody UserCreateRequest request) {
        var result = userCommandService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PatchMapping
    ResponseEntity<Void> updateUser(@RequestBody UserUpdateRequest request) {
        userCommandService.updateUser(request);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    ResponseEntity<Void> deleteUser(@RequestBody UserDeleteRequest request, Authentication authentication) {
        String authenticatedUserId = (String) authentication.getPrincipal();

        if (!authenticatedUserId.equals(request.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        userCommandService.deleteUser(request);
        return ResponseEntity.noContent().build();
    }
}

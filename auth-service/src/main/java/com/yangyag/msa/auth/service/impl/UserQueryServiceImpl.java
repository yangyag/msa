package com.yangyag.msa.auth.service.impl;

import com.yangyag.msa.auth.model.entity.User;
import com.yangyag.msa.auth.repository.UserRepository;
import com.yangyag.msa.auth.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findByUserId(String userId) {
        return null;
    }

    @Override
    public User findByUserIdAndPassword(String userId, String password) {
        return userRepository.findByUserIdAndPassword(userId, password)
                .orElseThrow(() -> new BadCredentialsException("Invalid userId or password"));
    }
}

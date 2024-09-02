package com.yangyag.msa.auth.service;

import com.yangyag.msa.auth.model.entity.User;

public interface UserQueryService {
    User findByUsername(String username);

    User findByEmail(String email);

    User findByUserId(String userId);

    User findByUserIdAndPassword(String userId, String password);
}

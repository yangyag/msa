package com.yangyag.msa.auth.service;

import com.yangyag.msa.auth.model.dto.UserCreateRequest;
import com.yangyag.msa.auth.model.entity.User;

public interface UserCommandService {
    User createUser(UserCreateRequest request);
}

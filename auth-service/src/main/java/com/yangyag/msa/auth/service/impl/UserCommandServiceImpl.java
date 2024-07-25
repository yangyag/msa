package com.yangyag.msa.auth.service.impl;

import com.yangyag.msa.auth.factory.UserCommandFactory;
import com.yangyag.msa.auth.model.dto.UserCreateRequest;
import com.yangyag.msa.auth.model.dto.UserUpdateRequest;
import com.yangyag.msa.auth.model.entity.User;
import com.yangyag.msa.auth.service.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserCommandFactory factory;

    @Override
    public User createUser(UserCreateRequest request) {
        return factory.createCommand(request).execute();
    }

    @Override
    public User updateUser(UserUpdateRequest request) {
        return factory.updateCommand(request).execute();
    }
}

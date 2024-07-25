package com.yangyag.msa.auth.factory;

import com.yangyag.msa.auth.factory.command.Command;
import com.yangyag.msa.auth.factory.command.UserCreateCommand;
import com.yangyag.msa.auth.factory.command.UserUpdateCommand;
import com.yangyag.msa.auth.model.dto.UserCreateRequest;
import com.yangyag.msa.auth.model.dto.UserUpdateRequest;
import com.yangyag.msa.auth.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCommandFactory {
    private final UserCreateCommand userCreateCommand;
    private final UserUpdateCommand userUpdateCommand;

    public Command<User> createCommand(UserCreateRequest request) {
        return userCreateCommand.withRequest(request);
    }

    public Command<User> updateCommand(UserUpdateRequest request) {
        return userUpdateCommand.withRequest(request);
    }
}

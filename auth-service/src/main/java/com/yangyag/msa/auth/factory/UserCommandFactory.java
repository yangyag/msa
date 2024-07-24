package com.yangyag.msa.auth.factory;

import com.yangyag.msa.auth.factory.command.Command;
import com.yangyag.msa.auth.factory.command.UserCreateCommand;
import com.yangyag.msa.auth.model.dto.UserCreateRequest;
import com.yangyag.msa.auth.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCommandFactory {
    private final UserCreateCommand userCreateCommand;

    public Command<User> createCommand(UserCreateRequest userCreateRequest) {
        return userCreateCommand.withRequest(userCreateRequest);
    }
}

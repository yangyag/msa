package com.yangyag.msa.auth.factory.command;

import com.yangyag.msa.auth.model.dto.UserCreateRequest;
import com.yangyag.msa.auth.model.entity.Role;
import com.yangyag.msa.auth.model.entity.User;
import com.yangyag.msa.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateCommand implements Command<User> {
    private final UserRepository userRepository;

    public Command<User> withRequest(UserCreateRequest request) {
        return () -> this.saveUser(request);
    }

    private User saveUser(UserCreateRequest request) {
        User user = User.builder()
                .userId(request.getUserId())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }

    @Override
    public User execute() {
        throw new IllegalStateException("이 메소드는 직접 실행할 수 없습니다. withRequest() 를 사용하세요.");
    }
}

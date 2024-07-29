package com.yangyag.msa.auth.factory.command;

import com.yangyag.msa.auth.model.dto.UserUpdateRequest;
import com.yangyag.msa.auth.model.entity.User;
import com.yangyag.msa.auth.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUpdateCommand implements Command<User> {
    private final UserRepository userRepository;

    public Command<User> withRequest(UserUpdateRequest request) {
        return () -> this.updateUser(request);
    }

    private User updateUser(UserUpdateRequest request) {
        return userRepository.findByUserId(request.getUserId())
                .map(user -> {
                    User updatedUser = User.builder()
                            .id(user.getId())
                            .userId(user.getUserId())
                            .username(Optional.ofNullable(request.getUsername()).orElse(user.getUsername()))
                            .email(Optional.ofNullable(request.getEmail()).orElse(user.getEmail()))
                            .password(Optional.ofNullable(request.getPassword()).orElse(user.getPassword()))
                            .build();
                    return userRepository.save(updatedUser);
                })
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format("%s 값을 찾을 수 없습니다.", request.getUserId())));
    }

    @Override
    public User execute() {
        throw new IllegalStateException("이 메소드는 직접 실행할 수 없습니다. withRequest() 를 사용하세요.");
    }
}
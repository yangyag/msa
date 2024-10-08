package com.yangyag.msa.auth.factory.command;

import com.yangyag.msa.auth.model.dto.UserDeleteRequest;
import com.yangyag.msa.auth.model.entity.Role;
import com.yangyag.msa.auth.model.entity.User;
import com.yangyag.msa.auth.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDeleteCommand implements Command<Boolean> {
    private final UserRepository userRepository;

    public Command<Boolean> withRequest(UserDeleteRequest request) {
        return () -> this.deleteUser(request);
    }

    private Boolean deleteUser(UserDeleteRequest request) {
        if (request.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("관리자만 사용자를 삭제할 수 있습니다.");
        }

        User user =
                userRepository
                        .findByUserId(request.getUserId())
                        .orElseThrow(
                                () ->
                                        new EntityNotFoundException(
                                                String.format(
                                                        "%s 값을 찾을 수 없습니다.", request.getUserId())));

        userRepository.delete(user);
        return true;
    }

    @Override
    public Boolean execute() {
        throw new IllegalStateException("이 메소드는 직접 실행할 수 없습니다. withRequest() 를 사용하세요.");
    }
}

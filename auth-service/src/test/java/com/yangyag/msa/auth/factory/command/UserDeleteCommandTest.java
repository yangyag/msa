package com.yangyag.msa.auth.factory.command;

import com.yangyag.msa.auth.model.dto.UserDeleteRequest;
import com.yangyag.msa.auth.model.entity.Role;
import com.yangyag.msa.auth.model.entity.User;
import com.yangyag.msa.auth.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserDeleteCommandTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDeleteCommand command;

    private User user;
    private UserDeleteRequest request;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .userId("test")
                .username("양현민")
                .email("test@gmail.com")
                .password("test!")
                .build();

        //given
        request = UserDeleteRequest.builder()
                .userId("test")
                .role(Role.ADMIN)
                .build();

    }

    @Test
    void shouldReturnTrueWhenUserExists() {
        //given
        given(userRepository.findByUserId(request.getUserId())).willReturn(Optional.ofNullable(user));

        //when
        var result = command.withRequest(request).execute();

        //then
        then(userRepository).should().delete(user);
        assertThat(result).isTrue();
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        //given
        given(userRepository.findByUserId(request.getUserId())).willReturn(Optional.empty());

        //when & then
        assertThatThrownBy(() -> command.withRequest(request).execute())
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(request.getUserId());
    }
}
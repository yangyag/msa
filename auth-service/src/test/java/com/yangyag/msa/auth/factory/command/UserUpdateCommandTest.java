package com.yangyag.msa.auth.factory.command;

import com.yangyag.msa.auth.model.dto.UserUpdateRequest;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserUpdateCommandTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserUpdateCommand userUpdateCommand;

    private User existingUser;
    private UserUpdateRequest updateRequest;

    @BeforeEach
    void setUp() {
        existingUser = User.builder()
                .id(1L)
                .userId("yangyag")
                .username("기존이름")
                .email("yangyag@hanmail.net")
                .password("1111")
                .role(Role.USER)
                .build();

        updateRequest = UserUpdateRequest.builder()
                .userId("yangyag")
                .username("새이름")
                .email("yangyag@gmail.com")
                .password("2222")
                .role(Role.USER)
                .build();
    }

    @Test
    void shouldSuccessfullyUpdateUser() {
        // given
        User updatedUser = User.builder()
                .id(existingUser.getId())
                .userId(existingUser.getUserId())
                .username(updateRequest.getUsername())
                .email(updateRequest.getEmail())
                .password(updateRequest.getPassword())
                .role(existingUser.getRole())
                .build();

        given(userRepository.findByUserId(updateRequest.getUserId())).willReturn(Optional.of(existingUser));
        given(userRepository.save(any(User.class))).willReturn(updatedUser);

        // when
        User result = userUpdateCommand.withRequest(updateRequest).execute();

        // then
        then(userRepository).should().findByUserId(updateRequest.getUserId());
        then(userRepository).should().save(any(User.class));

        assertThat(result)
                .isNotNull()
                .satisfies(user -> {
                    assertThat(user.getId()).isEqualTo(existingUser.getId());
                    assertThat(user.getUserId()).isEqualTo(existingUser.getUserId());
                    assertThat(user.getUsername()).isEqualTo(updateRequest.getUsername());
                    assertThat(user.getEmail()).isEqualTo(updateRequest.getEmail());
                    assertThat(user.getPassword()).isEqualTo(updateRequest.getPassword());
                    assertThat(user.getRole()).isEqualTo(updateRequest.getRole());
                });

        // 기존 사용자에 대한 정보는 변경되지 않아야 한다.
        assertThat(existingUser.getUsername()).isEqualTo("기존이름");
        assertThat(existingUser.getEmail()).isEqualTo("yangyag@hanmail.net");
        assertThat(existingUser.getPassword()).isEqualTo("1111");
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // given
        given(userRepository.findByUserId(updateRequest.getUserId())).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> userUpdateCommand.withRequest(updateRequest).execute())
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(updateRequest.getUserId());
    }
}
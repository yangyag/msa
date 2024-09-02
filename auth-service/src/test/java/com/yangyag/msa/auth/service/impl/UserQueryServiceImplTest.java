package com.yangyag.msa.auth.service.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.yangyag.msa.auth.model.entity.User;
import com.yangyag.msa.auth.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;

@ExtendWith(MockitoExtension.class)
class UserQueryServiceImplTest {

    @Mock private UserRepository userRepository;

    @InjectMocks private UserQueryServiceImpl userQueryService;

    @Test
    void shouldReturnUserWhenProvidedValidUserIdAndPassword() {
        User user =
                User.builder()
                        .id(1L)
                        .username("양현민")
                        .userId("yangyag")
                        .password("yangyag1")
                        .email("yangyag@gmail.com")
                        .build();

        // given
        given(userRepository.findByUserIdAndPassword(anyString(), anyString()))
                .willReturn(Optional.ofNullable(user));

        // when
        User result = userQueryService.findByUserIdAndPassword("yangyag", "yangyag1");

        // then
        then(result).isEqualTo(user);
    }

    @Test
    void shouldThrowExceptionWhenProvidedInvalidUserIdAndPassword() {
        User user =
                User.builder()
                        .id(1L)
                        .username("양현민")
                        .userId("yangyag")
                        .password("yangyag1")
                        .email("yangyag@gmail.com")
                        .build();

        // given
        given(userRepository.findByUserIdAndPassword("yangyag", "yangyag1"))
                .willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> userQueryService.findByUserIdAndPassword("yangyag", "yangyag1"))
                .isInstanceOf(BadCredentialsException.class)
                .hasMessage("Invalid userId or password");
    }
}

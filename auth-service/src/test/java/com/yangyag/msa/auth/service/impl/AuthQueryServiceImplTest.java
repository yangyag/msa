package com.yangyag.msa.auth.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.yangyag.msa.auth.model.entity.Role;
import com.yangyag.msa.auth.model.entity.User;
import com.yangyag.msa.auth.service.JwtService;
import com.yangyag.msa.auth.service.UserQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;

@ExtendWith(MockitoExtension.class)
class AuthQueryServiceImplTest {

    @Mock private JwtService jwtService;

    @Mock private UserQueryService userQueryService;

    @InjectMocks private AuthQueryServiceImpl authService;

    @Test
    void shouldReturnJwtTokenWhenProvidedValidUserIdAndPassword() {
        User user =
                User.builder()
                        .id(1L)
                        .username("양현민")
                        .userId("yangyag")
                        .password("yangyag1")
                        .email("yangyag@gmail.com")
                        .role(Role.ADMIN)
                        .build();

        // given
        given(userQueryService.findByUserIdAndPassword(anyString(), anyString())).willReturn(user);
        given(jwtService.generateToken(anyString(), anyString(), eq(Role.ADMIN))).willReturn("jwt");

        // when
        var result = authService.authenticate("yangyag", "yangyag1");

        // then
        assertThat(result).isEqualTo("jwt");
        then(userQueryService).should().findByUserIdAndPassword("yangyag", "yangyag1");
        then(jwtService).should().generateToken(eq("yangyag"), eq("양현민"), eq(Role.ADMIN));
    }

    @Test
    void shouldThrowExceptionWhenProvidedInvalidUserIdAndPassword() {
        // given
        given(userQueryService.findByUserIdAndPassword(anyString(), anyString())).willReturn(null);

        // when & then
        assertThatThrownBy(() -> authService.authenticate("yangyag", "yangyag1"))
                .isInstanceOf(BadCredentialsException.class)
                .hasMessage("Invalid userId or password");
    }
}

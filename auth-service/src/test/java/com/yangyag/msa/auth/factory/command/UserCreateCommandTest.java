package com.yangyag.msa.auth.factory.command;

import com.yangyag.msa.auth.model.dto.UserCreateRequest;
import com.yangyag.msa.auth.model.entity.User;
import com.yangyag.msa.auth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserCreateCommandTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserCreateCommand userCreateCommand;

    @Test
    void shouldSuccessfullyCreateUser() {
        UserCreateRequest request = UserCreateRequest.builder()
                .username("테스트")
                .userId("yangyag")
                .email("yangyag@gmail.com")
                .build();

        User savedUser = User.builder()
                .id(1L)
                .userId(request.getUserId())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        //given
        given(userRepository.save(any(User.class))).willReturn(savedUser);

        //when
        var result = userCreateCommand.withRequest(request).execute();

        //then
        then(userRepository).should().save(any(User.class));

        assertThat(result.getUserId()).isEqualTo("yangyag");
    }
}
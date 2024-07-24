package com.yangyag.msa.auth.service.impl;

import com.yangyag.msa.auth.config.TestSecurityConfig;
import com.yangyag.msa.auth.factory.UserCommandFactory;
import com.yangyag.msa.auth.model.dto.UserCreateRequest;
import com.yangyag.msa.auth.model.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TestSecurityConfig.class})
class UserCommandServiceImplTest {

    @Mock
    private UserCommandFactory factory;

    @InjectMocks
    private UserCommandServiceImpl userCommandService;

    @Test
    void shouldCreateUserWhenValidRequest() {
        UserCreateRequest request = mock(UserCreateRequest.class);
        User user = User.builder().userId("yangyag").build();

        //given
        given(factory.createCommand(request)).willReturn(() -> user);

        //when
        var result = userCommandService.createUser(request);

        //then
        then(factory).should().createCommand(request);
        assertThat(result.getUserId()).isEqualTo("yangyag");
    }
}
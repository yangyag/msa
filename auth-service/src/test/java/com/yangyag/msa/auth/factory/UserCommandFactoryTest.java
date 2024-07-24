package com.yangyag.msa.auth.factory;

import com.yangyag.msa.auth.factory.command.UserCreateCommand;
import com.yangyag.msa.auth.model.dto.UserCreateRequest;
import com.yangyag.msa.auth.model.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserCommandFactoryTest {

    @Mock
    private UserCreateCommand userCreateCommand;

    @InjectMocks
    private UserCommandFactory userCommandFactory;

    @Test
    void shouldResultUserWhenCalledCrateUserCommand() {
        UserCreateRequest userCreateRequest = mock(UserCreateRequest.class);
        User user = User.builder().userId("yangyag").build();

        //given
        given(userCreateCommand.withRequest(userCreateRequest)).willReturn(() -> user);

        //when
        var reuslt = userCommandFactory.createCommand(userCreateRequest).execute();

        //then
        then(userCreateCommand).should().withRequest(userCreateRequest);

        assertThat(reuslt.getUserId()).isEqualTo(user.getUserId());
    }
}
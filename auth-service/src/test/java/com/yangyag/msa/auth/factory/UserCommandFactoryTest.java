package com.yangyag.msa.auth.factory;

import static org.mockito.BDDMockito.*;

import com.yangyag.msa.auth.factory.command.UserCreateCommand;
import com.yangyag.msa.auth.factory.command.UserDeleteCommand;
import com.yangyag.msa.auth.factory.command.UserUpdateCommand;
import com.yangyag.msa.auth.model.dto.UserCreateRequest;
import com.yangyag.msa.auth.model.dto.UserDeleteRequest;
import com.yangyag.msa.auth.model.dto.UserUpdateRequest;
import com.yangyag.msa.auth.model.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserCommandFactoryTest {

    @Mock private UserCreateCommand userCreateCommand;

    @Mock private UserUpdateCommand userUpdateCommand;

    @Mock private UserDeleteCommand userDeleteCommand;

    @InjectMocks private UserCommandFactory userCommandFactory;

    @Test
    void shouldSuccessfullyCalledCreateCommandMethod() {
        UserCreateRequest request = mock(UserCreateRequest.class);
        User user = User.builder().userId("yangyag").build();

        // given
        given(userCreateCommand.withRequest(request)).willReturn(() -> user);

        // when
        userCommandFactory.createCommand(request).execute();

        // then
        then(userCreateCommand).should().withRequest(request);
    }

    @Test
    void shouldSuccessfullyCalledUpdateCommandMethod() {
        UserUpdateRequest request = mock(UserUpdateRequest.class);
        User user = User.builder().userId("yangyag").build();

        // given
        given(userUpdateCommand.withRequest(request)).willReturn(() -> user);

        // when
        userCommandFactory.updateCommand(request).execute();

        // then
        then(userUpdateCommand).should().withRequest(request);
    }

    @Test
    void shouldSuccessfullyCalledDeleteCommandMethod() {
        UserDeleteRequest request = mock(UserDeleteRequest.class);

        // given
        given(userDeleteCommand.withRequest(request)).willReturn(() -> true);

        // when
        userCommandFactory.deleteCommand(request).execute();

        // then
        then(userDeleteCommand).should().withRequest(request);
    }
}

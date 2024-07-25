package com.yangyag.msa.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangyag.msa.auth.config.TestSecurityConfig;
import com.yangyag.msa.auth.model.dto.UserCreateRequest;
import com.yangyag.msa.auth.model.dto.UserUpdateRequest;
import com.yangyag.msa.auth.service.UserCommandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ContextConfiguration(classes = {TestSecurityConfig.class})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserCommandService userCommandService;


    @Test
    void shouldSuccessfullyCreateUserWhenValidRequest() throws Exception {
        UserCreateRequest request = UserCreateRequest.builder()
                .userId("yangyag")
                .email("yangayg@gmail.com")
                .password("yangayg1")
                .username("양현민")
                .build();

        //when&then
        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldSuccessfullyUpdateUserWhenValidRequest() throws Exception {
        UserUpdateRequest request = UserUpdateRequest.builder()
                .userId("yangyag")
                .email("yangayg@gmail.com")
                .password("yangayg1")
                .username("양현민")
                .build();

        //when&then
        mockMvc.perform(patch("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());
    }
}
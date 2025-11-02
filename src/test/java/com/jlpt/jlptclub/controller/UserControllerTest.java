package com.jlpt.jlptclub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jlpt.jlptclub.domain.User;
import com.jlpt.jlptclub.repository.UserRepository;
import com.jlpt.jlptclub.service.UserService;
import com.jlpt.jlptclub.utils.CurrentLevel;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

@WebMvcTest(value = UserController.class,excludeAutoConfiguration =SecurityAutoConfiguration.class) // 只加载Web层，不加载Service和Repository
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean  // 关键：Mock UserService，避免真实数据库操作
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    void register() throws Exception {
        // 准备模拟数据
        User mockUser = new User();
        mockUser.setUserId(1L);  // 模拟数据库生成的ID
        mockUser.setUsername("challenger");
        mockUser.setEmail("1019262540@qq.com");
        mockUser.setCurrentLevel(CurrentLevel.N2);

        // Mock UserService的行为
        when(userService.save(any(User.class))).thenReturn(mockUser);

        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"challenger\",\"email\":\"1019262540@qq.com\",\"password\":\"123456\",\"currentLevel\":\"N2\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(1L));
    }

    @Test
    void loginSuccess() throws Exception {
        User mockUser = new User();
        mockUser.setUserId(1L);
        mockUser.setEmail("1019262540@qq.com");

        // Mock UserService.login() 的行为
        when(userService.login("1019262540@qq.com", "123456")).thenReturn(mockUser);
        // 2. 如果必须保留检查，Mock getUserRepository()
        when(userService.getUserRepository()).thenReturn(userRepository);  // userRepository 是 @MockitoBean
        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", "1019262540@qq.com")
                        .param("password", "123456"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(1L));
    }
    @Test
    void loginFailure() throws Exception {
        // Mock 登录失败（抛出异常）
        when(userService.login("wrong@qq.com", "wrong"))
                .thenThrow(new RuntimeException("用户名或密码错误"));

        // 发起请求并验证
        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", "wrong@qq.com")
                        .param("password", "wrong"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("登录失败")));
    }
}
package com.jlpt.jlptclub.service;

import com.jlpt.jlptclub.domain.User;
import com.jlpt.jlptclub.service.Impl.UserServiceImpl;
import com.jlpt.jlptclub.utils.CurrentLevel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Test
    void login() throws Exception {
        User user = userServiceImpl.login("1019262540@qq.com", "123456");
        System.out.println(user);
    }

    @Test
    void save() throws Exception {
        User user=new User();
        user.setEmail("1019262540@qq.com");
        user.setUsername("challenger");
        user.setPassword("123456");
        user.setCurrentLevel(CurrentLevel.N2);
        User save = userServiceImpl.save(user);
        System.out.println("================"+save+"==========================");
    }
}
package com.jlpt.jlptclub.repository;

import com.jlpt.jlptclub.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findUserByEmail() {
        User user = userRepository.findUserByEmail("1019262540@qq.com");
        System.out.println(user+"==============================");
    }

    @Test
    void findByEmailAndPassword() {
        User user = userRepository.findByEmailAndPassword("1019262540@qq.com","123456");
        System.out.println("====================="+user+"==========================");
    }
}
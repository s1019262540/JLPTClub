package com.jlpt.jlptclub.controller;

import com.jlpt.jlptclub.domain.User;
import com.jlpt.jlptclub.repository.UserRepository;
import com.jlpt.jlptclub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password){

        try {
            User user = userService.login(email, password);
            if (userService==null){
                return ResponseEntity.badRequest().body("登录失败：userService为空");
            }
            if (userService.getUserRepository() == null) {
                return ResponseEntity.badRequest().body("登录失败：userRepository为空");
            }
            return ResponseEntity
                    .created(URI.create("users"+user.getUserId()))
                    .body(user);
        }catch(Exception e){
            return ResponseEntity
                    .badRequest()
                    .body("登录失败"+e.getMessage());
        }

    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User save = userService.save(user);
            return ResponseEntity
                    .created(URI.create("/users/" + save.getUserId()))
                    .body(save);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("创建用户失败: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId){
        return this.userRepository.findById(userId).get();
    }

}

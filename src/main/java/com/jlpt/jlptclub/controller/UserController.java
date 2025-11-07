package com.jlpt.jlptclub.controller;

import com.jlpt.jlptclub.domain.User;
import com.jlpt.jlptclub.repository.UserRepository;
import com.jlpt.jlptclub.service.Impl.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpResponse;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserServiceImpl userServiceImpl;
    private final UserRepository userRepository;

    public UserController(UserServiceImpl userServiceImpl, UserRepository userRepository) {
        this.userServiceImpl = userServiceImpl;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password, HttpServletResponse  response){

        try {
            User user = userServiceImpl.login(email, password);
            if (userServiceImpl ==null){
                return ResponseEntity.badRequest().body("登录失败：userService为空");
            }
            if (userServiceImpl.getUserRepository() == null) {
                return ResponseEntity.badRequest().body("登录失败：userRepository为空");
            }
            Cookie cookie=new Cookie("user",user.getUserId().toString());
            response.addCookie(cookie);
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
            User save = userServiceImpl.save(user);
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

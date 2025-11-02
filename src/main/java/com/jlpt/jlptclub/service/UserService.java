package com.jlpt.jlptclub.service;

import com.jlpt.jlptclub.domain.User;
import com.jlpt.jlptclub.repository.UserRepository;
import com.jlpt.jlptclub.utils.BCryptPasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;

@Service
public class UserService{
    private final UserRepository userRepository;
    public UserRepository getUserRepository(){
        return this.userRepository;
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String email, String password)throws Exception {
        User user = userRepository.findUserByEmail(email);
        if(user==null||Boolean.FALSE.equals(BCryptPasswordUtil.matches(password,user.getPassword()))){
            //返回对象
            throw new Exception("账户或密码错误");
        }
        return user;
    }

    public User save(User user) throws Exception {
        String encode = BCryptPasswordUtil.encode(user.getPassword());
        user.setPassword(encode);
        User save = userRepository.save(user);

        if(save==null){
            throw new Exception("注册失败");
        }


        return save;
    }
}

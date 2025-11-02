package com.jlpt.jlptclub.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 将 "/" 映射到首页视图（如index.html）
        registry.addViewController("/").setViewName("redirect:/login");

        // 登录页映射
        registry.addViewController("/login").setViewName("login");

        // 注册页映射
        registry.addViewController("/register").setViewName("register");
    }
}

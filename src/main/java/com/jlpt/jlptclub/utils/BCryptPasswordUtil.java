package com.jlpt.jlptclub.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码加密工具类
 * 使用Spring Security的BCrypt强哈希算法加密密码
 */
public class BCryptPasswordUtil {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    /**
     * 加密密码
     * @param rawPassword 原始密码
     * @return 加密后的密码
     */
    public static String encode(String rawPassword) {
        return PASSWORD_ENCODER.encode(rawPassword);
    }

    /**
     * 验证密码是否匹配
     * @param rawPassword 原始密码（用户输入的密码）
     * @param encodedPassword 加密后的密码（数据库中存储的密码）
     * @return 匹配返回true，否则返回false
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return PASSWORD_ENCODER.matches(rawPassword, encodedPassword);
    }

    /**
     * 生成随机密码（可用于密码重置等功能）
     * @param length 密码长度
     * @return 随机生成的密码
     */
    public static String generateRandomPassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("密码长度不能少于8位");
        }

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    /**
     * 判断密码是否需要重新加密（当加密算法升级时使用）
     * @param encodedPassword 已加密的密码
     * @return 如果需要重新加密返回true，否则返回false
     */
    public static boolean needsUpgrade(String encodedPassword) {
        return PASSWORD_ENCODER.upgradeEncoding(encodedPassword);
    }
}

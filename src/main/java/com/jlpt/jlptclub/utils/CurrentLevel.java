package com.jlpt.jlptclub.utils;

public enum CurrentLevel {
    N1("一级"),N2("二级"),N3("三级"),N4("四级"),N5("五级");
    private String level;

    CurrentLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    public class EnumMatcher {
        public static CurrentLevel matchLevel(String input) {
            try {
                return CurrentLevel.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                return null; // 或者抛出异常
            }
        }
    }
}

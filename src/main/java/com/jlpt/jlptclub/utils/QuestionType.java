package com.jlpt.jlptclub.utils;

public enum QuestionType {
    SINGLE_CHOICE("single_choice"),     // 单项选择题
    MULTIPLE_CHOICE("multiple_choice"), // 多项选择题
    TRUE_FALSE("true_false"),           // 判断题
    FILL_IN_THE_BLANK("fill_in_the_blank"), // 填空题
    SHORT_ANSWER("short_answer"),       // 简答题
    LISTENING("listening"),             // 听力题
    READING("reading"),                 // 阅读理解题
    GRAMMAR("grammar"),                 //语法题
    VOCABULARY("vocabulary"),           //词汇题

    TRANSLATION("translation"),         // 翻译题
    ORDERING("ordering"),               // 排序题
    MATCHING("matching");               // 配对题

    private final String value;

    QuestionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static QuestionType fromValue(String value) {
        for (QuestionType type : QuestionType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown question type: " + value);
    }
}


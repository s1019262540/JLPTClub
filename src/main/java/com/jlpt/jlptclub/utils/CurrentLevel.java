package com.jlpt.jlptclub.utils;

public enum CurrentLevel {
    N1("一级",41,26,37,27),
    N2("二级",40,21,32,23),
    N3("三级",20,33,28,26),
    N4("四级",20,26,27,23),
    N5("五级",20,23,24,20);
    private String level;
    private int listeningCount;
    private int readingCount;
    private int wordCount;
    private int grammarCount;

    CurrentLevel(String level,int listeningCount,int readingCount,int wordCount,int grammarCount) {
        this.level = level;
        this.readingCount = readingCount;
        this.listeningCount = listeningCount;
        this.wordCount = wordCount;
        this.grammarCount = grammarCount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getListeningCount() {
        return listeningCount;
    }

    public void setListeningCount(int listeningCount) {
        this.listeningCount = listeningCount;
    }

    public int getReadingCount() {
        return readingCount;
    }

    public void setReadingCount(int readingCount) {
        this.readingCount = readingCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void WordCount(int wordCount) {
        this.wordCount = wordCount;
    }
    public int getGrammarCount() {
        return grammarCount;
    }

    public void setGrammarCount(int wordGrammarCount) {
        this.grammarCount= grammarCount;
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

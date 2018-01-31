package com.example.mariamarnerou.testapp.model;

import java.io.Serializable;
import java.util.Arrays;

public class Planet implements Serializable {

    private String name;
    private String welcome;
    private Question[] questions;

    public Planet() { super(); }

    public String getName() {
        return name;
    }

    public String getWelcome() {
        return welcome;
    }

    public int getNumOfQuestions() {
        return questions.length;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public Question getQuestion(int i) {
        return questions[i];
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", welcome='" + welcome + '\'' +
                ", questions=" + Arrays.toString(questions) +
                '}';
    }
}
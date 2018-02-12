package com.example.mariamarnerou.testapp.model;

import java.io.Serializable;
import java.util.Arrays;

public class Question implements Serializable {

    private String question;
    private String [] answers;
    private int correct;

    public Question(String q, String[] a, int c) { super(); }

    public String getQuestion() {
        return question;
    }

    public String [] getAnswers() {
        return answers;
    }

    public int getNumOfAnswers() {
        return answers.length;
    }

    public String getAnswer(final int i) {
        return answers[i];
    }

    public int getCorrect() {
        return correct;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answers=" + Arrays.toString(answers) +
                ", correct=" + correct +
                '}';
    }
}
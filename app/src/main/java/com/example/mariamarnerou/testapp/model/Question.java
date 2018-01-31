package com.example.mariamarnerou.testapp.model;

import java.io.Serializable;
import java.util.Arrays;

public class Question implements Serializable {

    public String question;
    public String [] answers,questions;
    public int correct;

    public Question() { super(); }

    public String[] getQuestions() {
        return questions;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
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
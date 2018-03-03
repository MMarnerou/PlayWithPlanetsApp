package com.example.mariamarnerou.testapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mariamarnerou.testapp.model.Planet;
import com.example.mariamarnerou.testapp.model.Question;
import com.example.mariamarnerou.testapp.model.Quiz;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FinalQuiz extends AppCompatActivity {
    TextView questionTextTextView, questionIdTextView, planetTextView;
    Button answer1Btn, answer2Btn, answer3Btn, answer4Btn, nextBtn;

    private Quiz quiz;
    private Planet planet;
    private int currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_quiz);
        answer1Btn = findViewById(R.id.answer1);
        answer2Btn = findViewById(R.id.answer2);
        answer3Btn = findViewById(R.id.answer3);
        answer4Btn = findViewById(R.id.answer4);
        planetTextView = findViewById(R.id.planet);
        questionTextTextView = findViewById(R.id.question);
        questionIdTextView = findViewById(R.id.questionId);
        nextBtn = findViewById(R.id.next);

        try {
            final InputStream inputStream = getAssets().open("questions.json");
            quiz = new Gson().fromJson(new InputStreamReader(inputStream), Quiz.class);
            Log.d("planets", quiz.toString());
        } catch (IOException ioe) {
            Log.e("planets", ioe.getMessage(), ioe);
        }

        planet = quiz.getPlanet("Sun");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Use preferences to 'remember' the currentQuestion in case the user leaves the activity before finishing

    }

    @Override
    protected void onResume() {
        super.onResume();

        currentQuestion = 0;

        planetTextView.setText(planet.getName());

        showQuestion();
    }

    private void showQuestion() {
        // show current question
        questionIdTextView.setText("#" + (currentQuestion + 1) + "/" + planet.getNumOfQuestions());

        final Question question = planet.getQuestion(currentQuestion);
        questionTextTextView.setText(question.getQuestion());

        answer1Btn.setEnabled(true);
        answer2Btn.setEnabled(true);
        answer3Btn.setEnabled(true);
        answer4Btn.setEnabled(true);

        answer1Btn.setBackgroundColor(Color.GRAY);
        answer2Btn.setBackgroundColor(Color.GRAY);
        answer3Btn.setBackgroundColor(Color.GRAY);
        answer4Btn.setBackgroundColor(Color.GRAY);

        answer1Btn.setText(question.getAnswer(0));
        answer2Btn.setText(question.getAnswer(1));
        answer3Btn.setText(question.getAnswer(2));
        answer4Btn.setText(question.getAnswer(3));

        //Disable nextBtn button
        nextBtn.setEnabled(false);
    }

    public void checkAnswer(View view) {
        int answerIndex;
        if(view == answer1Btn) {
            answerIndex = 0;
        } else if(view == answer2Btn) {
            answerIndex = 1;
        } else if(view == answer3Btn) {
            answerIndex = 2;
        } else { // assume answer4Btn
            answerIndex = 3;
        }

        view.setEnabled(false);

        final Question question = planet.getQuestion(currentQuestion);
        final boolean correct = question.getCorrect() == answerIndex;

        Log.d("planets", "answerIndex: " + answerIndex + ", correct: " + question.getCorrect() + ", correct: " + correct);

        if(correct) {
            view.setBackgroundColor(Color.GREEN);
            answer1Btn.setEnabled(false);
            answer2Btn.setEnabled(false);
            answer3Btn.setEnabled(false);
            answer4Btn.setEnabled(false);
            nextBtn.setEnabled(true);
        } else {
            view.setBackgroundColor(Color.RED);
            nextBtn.setEnabled(false);
        }
    }

    public void nextQuestion(View view) {
        currentQuestion++;
        if(currentQuestion == planet.getNumOfQuestions()) {
            finish(); // exit activity
        } else {
            showQuestion();
        }
    }
}

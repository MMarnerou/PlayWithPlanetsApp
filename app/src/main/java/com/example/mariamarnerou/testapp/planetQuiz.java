package com.example.mariamarnerou.testapp;

import android.content.Intent;
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

public class planetQuiz extends AppCompatActivity { // todo you need to name classes starting with capital letters. you can fix this using 'refactoring'. select the class name 'planetQuiz' and then press SHIFT+F6 and edit the name. using refactoring will fix the class name and all places where it is used.

    TextView questionTxt, questionID, planet;
    Button answer1Btn, answer2Btn, answer3Btn, answer4Btn, next;
    Planet planetChoice;
    int correctAnswer;
    String planetName;
    Boolean correctFound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_quiz);

        answer1Btn = findViewById(R.id.answer1);
        answer2Btn = findViewById(R.id.answer2);
        answer3Btn = findViewById(R.id.answer3);
        answer4Btn = findViewById(R.id.answer4);
        planet = findViewById(R.id.planet);
        questionTxt = findViewById(R.id.question);
        questionID = findViewById(R.id.questionId);
        next = findViewById(R.id.next);

        Intent quizIntent = getIntent();
        planetName = (String) quizIntent.getSerializableExtra("planet");

        questionTxt.setText("Question 1");
        answer1Btn.setText("Answer 1");
        answer2Btn.setText("Answer 2");
        answer3Btn.setText("Answer 3");
        answer4Btn.setText("Answer 4");

        try {
            final InputStream inputStream = getAssets().open("questions.json");
            final Quiz quiz = new Gson().fromJson(new InputStreamReader(inputStream), Quiz.class);
            planetChoice = quiz.getPlanet(planetName);
            final Question[] questionArray = planetChoice.getQuestions();

            planet.setText("Welcome to " + planetChoice.getName());
            for (int i = 0; i < 1; i++) {
                next.setEnabled(false);

                questionTxt.setText(questionArray[i].getQuestion());
                answer1Btn.setText(questionArray[i].getAnswer(0));
                answer2Btn.setText(questionArray[i].getAnswer(1));
                answer3Btn.setText(questionArray[i].getAnswer(2));
                answer4Btn.setText(questionArray[i].getAnswer(3));
                correctAnswer = questionArray[i].getCorrect();
//                Toast.makeText(this, "Correct Answer is: " + correctAnswer, LENGTH_LONG).show();
                questionID.setText("Q" + (i + 1) + "/" + 10);

                answer1Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isCorrect(v, 0, correctFound);
                    }
                });
                answer2Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isCorrect(v, 1, correctFound);
                    }
                });
                answer3Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isCorrect(v, 2, correctFound);
                    }
                });
                answer4Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isCorrect(v, 3, correctFound);
                    }
                });
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        questionTxt.setText("");
                    }
                });
            }

        } catch (IOException ioe) {
            Log.e("planets", ioe.getMessage(), ioe);
        }
    }

    public void isCorrect(View button, int correct, boolean found) {
        if (correctAnswer == correct) {
            button.setBackgroundColor(Color.GREEN);
            Toast.makeText(planetQuiz.this, "This is the right answer", Toast.LENGTH_SHORT).show();
            found = true;
            next.setEnabled(true);
        } else {
            button.setBackgroundColor(Color.RED);
            Toast.makeText(planetQuiz.this, "This is not the right answer. Try Again", Toast.LENGTH_SHORT).show();
            found = false;
        }
    }
}
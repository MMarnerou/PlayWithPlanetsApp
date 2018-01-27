package com.example.mariamarnerou.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.mariamarnerou.testapp.model.Planet;
import com.example.mariamarnerou.testapp.model.Quiz;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class planetQuiz extends AppCompatActivity {

    TextView questionTxt, questionID, planet;
    Button answer1Btn, answer2Btn, answer3Btn, answer4Btn;
    Planet planetChoice;
    int numOfAnswer;
    String planetName;

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
            Question[] questionArray = planetChoice.getQuestions();

            planet.setText("Welcome to " + planetChoice.getName());

            questionTxt.setText(questionArray[0].getQuestion());
            answer1Btn.setText(planetName);
//            answer2Btn.setText(questionArray[0].getAnswer(1));
//            answer3Btn.setText(questionArray[0].getAnswer(2));
//            answer4Btn.setText(questionArray[0].getAnswer(3));
            numOfAnswer = questionArray[0].getNumOfAnswers();
            questionID.setText("Q" + 1 + "/" + numOfAnswer);
        } catch (IOException ioe) {
            Log.e("planets", ioe.getMessage(), ioe);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

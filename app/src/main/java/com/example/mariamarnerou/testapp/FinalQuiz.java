package com.example.mariamarnerou.testapp;

import android.content.Intent;
import android.content.SharedPreferences;
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Calendar;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class FinalQuiz extends AppCompatActivity {
    TextView questionTextTextView, questionIdTextView, planetTextView;
    Button answer1Btn, answer2Btn, answer3Btn, answer4Btn, nextBtn;
    int[] userAnswers = new int[11];
    String username, finishedMode;
    int score;
    private Quiz quiz;
    private Planet planet;
    private int currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_quiz);

        score = 0;

        Intent usernameIntent = getIntent();
        username = usernameIntent.getStringExtra("username");
        finishedMode = usernameIntent.getStringExtra("completedMode");

        answer1Btn = findViewById(R.id.answer1);
        answer2Btn = findViewById(R.id.answer2);
        answer3Btn = findViewById(R.id.answer3);
        answer4Btn = findViewById(R.id.answer4);
        planetTextView = findViewById(R.id.planet);
        questionTextTextView = findViewById(R.id.question);
        questionIdTextView = findViewById(R.id.questionId);
        nextBtn = findViewById(R.id.next);

        //Specify the planet object through Gson request
        try {
            final InputStream inputStream = getAssets().open("questions.json");
            quiz = new Gson().fromJson(new InputStreamReader(inputStream), Quiz.class);
        } catch (IOException ioe) {
            Log.e("planets", ioe.getMessage(), ioe);
        }
        planet = quiz.getPlanet("'Ηλιος");
    }

    @Override
    protected void onResume() {
        super.onResume();

        final SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(currentQuestion), MODE_PRIVATE);
        currentQuestion = sharedPreferences.getInt("currentQuestion", 0);
        showQuestion();
    }

    private void showQuestion() {
        // show current question
        questionIdTextView.setText("#" + (currentQuestion + 1) + "/" + planet.getNumOfQuestions());

        final Question question = planet.getQuestion(currentQuestion);
        planetTextView.setText(planet.getWelcome());
        questionTextTextView.setText(question.getQuestion());

        answer1Btn.setEnabled(true);
        answer2Btn.setEnabled(true);
        answer3Btn.setEnabled(true);
        answer4Btn.setEnabled(true);

        answer1Btn.setBackgroundColor(Color.WHITE);
        answer2Btn.setBackgroundColor(Color.WHITE);
        answer3Btn.setBackgroundColor(Color.WHITE);
        answer4Btn.setBackgroundColor(Color.WHITE);

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
        userAnswers[currentQuestion] = answerIndex;

        final Question question = planet.getQuestion(currentQuestion);
        final boolean correct = question.getCorrect() == answerIndex;

        Log.d("planets", "answerIndex: " + answerIndex + ", correct: " + question.getCorrect() + ", correct: " + correct);

        if(correct) {
            view.setBackgroundColor(Color.GREEN);
            score = score + 1;
        } else {
            Toast.makeText(this, "The correct answer is " + question.getAnswer(question.getCorrect()), Toast.LENGTH_SHORT).show();
            view.setBackgroundColor(Color.RED);
        }

        answer1Btn.setEnabled(false);
        answer2Btn.setEnabled(false);
        answer3Btn.setEnabled(false);
        answer4Btn.setEnabled(false);
        nextBtn.setEnabled(true);
    }

    public void nextQuestion(View view) {

        //Save the current question of the quiz
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor questionEditor = sharedPreferences.edit();
        questionEditor.putInt("currentQuestion", currentQuestion);
        questionEditor.commit();

        //when is the last question close activity and get Sharepreferences
        if ((currentQuestion + 1) == planet.getNumOfQuestions()) {
            Toast.makeText(this, "Συγχαρητήρια!! Έχεις ολοκληρώσει το ταξίδι εξερεύνησης του Ηλιακού μας συστήματος. ", Toast.LENGTH_SHORT).show();

            currentQuestion = 0;
            Toast.makeText(this, "Your score is " + score + " /10", Toast.LENGTH_SHORT).show();
            Write(userAnswers);
            finish(); // exit activity
        } else {
            currentQuestion++;
            showQuestion();
        }
    }

    public void Write(int intArray[]) {

        try {
            FileWriter fr = new FileWriter(username + Calendar.getInstance().getTime() + ".txt");
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter out = new PrintWriter(br);
            out.write("Username: " + username + "Datetime: " + Calendar.getInstance().getTime() + "\n");
            out.write("Finished Mode: " + finishedMode + "\n");
            for (int i = 0; i < intArray.length; i++) {
                out.write(intArray[i] + "\n");
            }
            out.write("Score: " + score);
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
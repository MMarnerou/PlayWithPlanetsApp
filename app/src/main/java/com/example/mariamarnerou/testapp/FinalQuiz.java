package com.example.mariamarnerou.testapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mariamarnerou.testapp.accountSqlDatabase.DatabaseOpenHelper;
import com.example.mariamarnerou.testapp.model.Planet;
import com.example.mariamarnerou.testapp.model.Question;
import com.example.mariamarnerou.testapp.model.Quiz;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class FinalQuiz extends AppCompatActivity {
    public static final int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 42;
    public static final String TAG = "playWithPlanets";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.US);
    TextView questionTextTextView, questionIdTextView, planetTextView;
    Button answer1Btn, answer2Btn, answer3Btn, answer4Btn, nextBtn;
    int[] userAnswers = new int[11];
    String username, finishedMode;
    int score;
    String age;
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
            Toast.makeText(this, "Η σωστή απάντηση είναι " + question.getAnswer(question.getCorrect()), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "Η βαθμολογία σου είναι " + score + " /10", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "calling exportAnswers");
            exportAnswers();
            finish(); // exit activity
        } else {
            currentQuestion++;
            showQuestion();
        }
    }

    private void exportAnswers() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            Log.d(TAG, "requestPermissions");
            requestPermissions(new String [] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                    PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            saveToFile();
            Log.d(TAG, "going directly to saveToFile");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            saveToFile();
            Log.d(TAG, "save success");
        } else {
            Toast.makeText(this, "You must allow the app to save the answers to proceed with this", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "save fail");
        }
    }

    private void saveToFile() {
        Log.d(TAG, "saveToFile");
        //OPEN THE DATABASE AND REQUEST FOR AN SPECIFIC username
        String sql = "SELECT * FROM accounts Where username='" + username + "'";
        DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(this);
        SQLiteDatabase db = databaseOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            age = cursor.getString(1);
        }
        final StringBuilder answers = new StringBuilder();
        answers.append("Username: ").append(username).append(" Age: ").append(age).append("\n");
        answers.append("Datetime: ").append(Calendar.getInstance().getTime()).append("\n");
        answers.append("Finished Mode: ").append(finishedMode).append("\n");
        answers.append("Answers: ").append(Arrays.toString(userAnswers)).append("\n");
        answers.append("Score: ").append(score).append("\n");

        final File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Log.d(TAG, "sdCard: " + sdCard);
        Log.d(TAG, "sdCard.exists(): " + sdCard.exists());
        final File dir = new File(sdCard.getAbsolutePath() + "/planets");
        if (dir.exists() || dir.mkdirs()) {
            File file = new File(dir, "Πλανητάριο-" + username + "-" + SIMPLE_DATE_FORMAT.format(new Date()) + ".txt");
            try {
                final PrintWriter printWriter = new PrintWriter(file);
                printWriter.println(answers.toString());
                printWriter.close();
                Toast.makeText(this, "File containing answers created in: " + dir.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException fnfe) {
                Toast.makeText(this, "Failed to create file: " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Failed to create dir: " + dir.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        }
    }
}
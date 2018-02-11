package com.example.mariamarnerou.testapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PlanetQuiz extends AppCompatActivity {
    TextView questionTxt, questionID, planet;
    Button answer1Btn, answer2Btn, answer3Btn, answer4Btn, next;
    int correctAnswer, i, j, planetPos;
    String planetName, name, welcome, informations, question;
    boolean isFound, planetFound;
    JSONObject planetsObject, jsonObject, questionObject;
    JSONArray questionsArray, jsonArray, answersArray;

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

        //Get extras (planet name) from Modes Activity
        Intent quizIntent = getIntent();
        planetName = (String) quizIntent.getSerializableExtra("planet");
        //Disable next button
        next.setEnabled(false);

        try {
            // Get full json object
            String json = getStringFromInputStream(getAssets().open("questions.json"));
            jsonObject = new JSONObject(json);
            jsonArray = jsonObject.getJSONArray("planets");

            //Loop through planets object until find the object
            planetFound = false;
            i = 0;
            while (i < jsonArray.length() && !planetFound) {
                planetsObject = jsonArray.getJSONObject(i);
                name = planetsObject.getString("name");
                //When the planet found, retrieve all the data from the object
                if (planetName.equals(name)) {
                    welcome = planetsObject.getString("welcome");
                    informations = planetsObject.getString("informations");
                    questionsArray = planetsObject.getJSONArray("questions");
                    planetPos = i;
                    planetFound = true;
                }
                i++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Loop through the questions of the planet object
        try {
            isFound = true; //The answer found
            do {
                //retrieve all the informations of each question
                questionObject = questionsArray.getJSONObject(planetPos);
                answersArray = questionObject.getJSONArray("answers");
                planet.setText(welcome);
                questionID.setText("Q " + (j + 1));
                question = questionObject.getString("question");
                correctAnswer = questionObject.getInt("correct");
                isFound = false;
                setQuestion(question, answersArray);
                //Buttons functionality : if it is the correct button enable next button
                answer1Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isCorrect(v, 0, correctAnswer);
                    }
                });
                answer2Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isCorrect(v, 1, correctAnswer);
                    }
                });
                answer3Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isCorrect(v, 2, correctAnswer);
                    }
                });
                answer4Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isCorrect(v, 3, correctAnswer);
                    }
                });

                //next button increase j by one to get the next question
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isFound = true;
                    }
                });
                j++;
            } while ((j < questionsArray.length()) && isFound);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "JsonException found", Toast.LENGTH_LONG).show();
        }
    }

    public void isCorrect(View button, int selectedAnswer, int correct) {
        if (selectedAnswer == correct) {
            button.setBackgroundColor(Color.GREEN);
            answer1Btn.setEnabled(false);
            answer2Btn.setEnabled(false);
            answer3Btn.setEnabled(false);
            answer4Btn.setEnabled(false);

            next.setEnabled(true);
        } else {
            button.setBackgroundColor(Color.RED);
        }
    }

    //set the fields of the question
    public void setQuestion(String question, JSONArray answers) throws JSONException {
        questionID.setText("Q " + (j + 1) + "/" + 10);
        answer1Btn.setBackgroundColor(Color.WHITE);
        answer2Btn.setBackgroundColor(Color.WHITE);
        answer3Btn.setBackgroundColor(Color.WHITE);
        answer4Btn.setBackgroundColor(Color.WHITE);
        questionTxt.setText(question);
        answer1Btn.setText(answers.getString(0));
        answer2Btn.setText(answers.getString(1));
        answer3Btn.setText(answers.getString(2));
        answer4Btn.setText(answers.getString(3));

    }

    // Get String from inputStream
    @NonNull
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

}

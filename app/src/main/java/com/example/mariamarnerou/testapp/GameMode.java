package com.example.mariamarnerou.testapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mariamarnerou.testapp.model.Quiz;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class GameMode extends AppCompatActivity implements View.OnClickListener {

    ImageButton neptuneBtn, uranusBtn, saturnBtn, jupiterBtn, marsBtn, earthBtn, venusBtn, mercuryBtn, sunBtn;
    boolean[] status = new boolean[9];
    Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        neptuneBtn = findViewById(R.id.neptune);
        uranusBtn = findViewById(R.id.uranus);
        saturnBtn = findViewById(R.id.saturn);
        jupiterBtn = findViewById(R.id.jupiter);
        marsBtn = findViewById(R.id.mars);
        venusBtn = findViewById(R.id.venus);
        earthBtn = findViewById(R.id.earth);
        mercuryBtn = findViewById(R.id.mercury);
        sunBtn = findViewById(R.id.sun);

        neptuneBtn.setOnClickListener(this);
        uranusBtn.setOnClickListener(this);
        saturnBtn.setOnClickListener(this);
        jupiterBtn.setOnClickListener(this);
        marsBtn.setOnClickListener(this);
        venusBtn.setOnClickListener(this);
        earthBtn.setOnClickListener(this);
        mercuryBtn.setOnClickListener(this);
        sunBtn.setOnClickListener(this);

        SharedPreferences prefs = getDefaultSharedPreferences(getApplicationContext());
        String completedPlanet = prefs.getString("completedPlanet", "Ποσειδώνας");
        Toast.makeText(this, completedPlanet, Toast.LENGTH_LONG).show();
        for (int k = 0; k < status.length; k++) {
            status[k] = false;
        }

        int i = 0;
        while (i < status.length) {
            try {
                final InputStream inputStream = getAssets().open("questions.json");
                quiz = new Gson().fromJson(new InputStreamReader(inputStream), Quiz.class);
                String planet = quiz.getPlanet(i).getName();
                if (planet != completedPlanet) {
                    status[i] = true;
                    i++;
                } else {
                    status[i] = true;
                    for (int j=i; j < status.length; j++) {
                        status[j] = false;
                    }
                    break;
                }
            } catch (IOException ioe) {
                Log.e("planetsStatus", ioe.getMessage(), ioe);
            }
        }

        //disabled all the quizes
        uranusBtn.setEnabled(status[1]);
        saturnBtn.setEnabled(status[2]);
        jupiterBtn.setEnabled(status[3]);
        marsBtn.setEnabled(status[4]);
        venusBtn.setEnabled(status[5]);
        earthBtn.setEnabled(status[6]);
        mercuryBtn.setEnabled(status[7]);
        sunBtn.setVisibility(View.INVISIBLE);

        if (completedPlanet == "Αφροδίτη") {
            sunBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        Intent quiz_intent = new Intent(this, PlanetQuiz.class);
        if (view == neptuneBtn) {
            quiz_intent.putExtra("planetTextView", "Ποσειδώνας");
        } else if (view == uranusBtn) {
            quiz_intent.putExtra("planetTextView", "Ουρανός");
        } else if (view == venusBtn) {
            quiz_intent.putExtra("planetTextView", "Ερμής");
        } else if (view == marsBtn) {
            quiz_intent.putExtra("planetTextView", "Άρης");
        } else if (view == jupiterBtn) {
            quiz_intent.putExtra("planetTextView", "Δίας");
        } else if (view == earthBtn) {
            quiz_intent.putExtra("planetTextView", "Γη");
        } else if (view == saturnBtn) {
            quiz_intent.putExtra("planetTextView", "Κρόνος");
        } else if (view == mercuryBtn) {
            quiz_intent.putExtra("planetTextView", "Αφροδίτη");
        }else if (view == sunBtn) {
            quiz_intent.putExtra("planetTextView", "Ήλιος");
        } else { // in case of unknown click, show log error and close activity
            Log.e("planets", "Unknown view clicked: " + view);
            finish();
        }

        startActivity(quiz_intent);
    }
    @Override
    protected void onResume() {
        super.onResume();


    }

}
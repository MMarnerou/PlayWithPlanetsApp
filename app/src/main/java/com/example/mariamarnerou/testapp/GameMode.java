package com.example.mariamarnerou.testapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class GameMode extends AppCompatActivity implements View.OnClickListener {

    ImageButton neptuneBtn, uranusBtn, saturnBtn, jupiterBtn, marsBtn, earthBtn, venusBtn, mercuryBtn, sunBtn;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        Intent usernameIntent = getIntent();
        username = usernameIntent.getStringExtra("username");

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

        if (username != null) {
            SharedPreferences prefs = getDefaultSharedPreferences(getApplicationContext());
            String completedPlanet = prefs.getString("completedPlanet", "Ποσειδώνας");
            Toast.makeText(this, "Η τελευταία επίσκεψή σου ήταν στον πλανήτη " + completedPlanet, Toast.LENGTH_SHORT).show();
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
            quiz_intent = new Intent(this, Modes.class);
            quiz_intent.putExtra("finishedMode", "GameMode");
            quiz_intent.putExtra("username", username);
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
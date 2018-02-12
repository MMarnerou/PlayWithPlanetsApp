package com.example.mariamarnerou.testapp;

import android.content.Intent;
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

public class GameMode extends AppCompatActivity implements View.OnClickListener {

    ImageButton plutoBtn, neptuneBtn, uranusBtn, saturnBtn, jupiterBtn, marsBtn, earthBtn, venusBtn, mercuryBtn, sunBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
        // todo your images are nice but the text in it os too small to be readable
        // todo make sure that any text/images/sounds you use are properly cited and that you only use free resources from the public domain
        // todo I see that when I play one of the planets (e.g. Neptune) when I click 'nextBtn question' nothing happens.

        plutoBtn = findViewById(R.id.pluto);
        neptuneBtn = findViewById(R.id.neptune);
        uranusBtn = findViewById(R.id.uranus);
        saturnBtn = findViewById(R.id.saturn);
        jupiterBtn = findViewById(R.id.jupiter);
        marsBtn = findViewById(R.id.mars);
        venusBtn = findViewById(R.id.venus);
        earthBtn = findViewById(R.id.earth);
        mercuryBtn = findViewById(R.id.mercury);
        //sunBtn = findViewById(R.id.sun);

        plutoBtn.setOnClickListener(this);
        neptuneBtn.setOnClickListener(this);
        uranusBtn.setOnClickListener(this);
        saturnBtn.setOnClickListener(this);
        jupiterBtn.setOnClickListener(this);
        marsBtn.setOnClickListener(this);
        venusBtn.setOnClickListener(this);
        earthBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
Toast.makeText(this, "View: " + view, Toast.LENGTH_SHORT).show();
        Intent quiz_intent = new Intent(this, PlanetQuiz.class);
        if (view == plutoBtn) {
            quiz_intent.putExtra("planetTextView", "Pluto");
        } else if (view == neptuneBtn) {
            quiz_intent.putExtra("planetTextView", "Neptune");
        } else if (view == uranusBtn) {
            quiz_intent.putExtra("planetTextView", "Uranus");
        } else if (view == mercuryBtn) {
            quiz_intent.putExtra("planetTextView", "Mercury");
        } else if (view == marsBtn) {
            quiz_intent.putExtra("planetTextView", "Mars");
        } else if (view == jupiterBtn) {
            quiz_intent.putExtra("planetTextView", "Jupiter");
        } else if (view == earthBtn) {
            quiz_intent.putExtra("planetTextView", "Earth");
        } else if (view == saturnBtn) {
            quiz_intent.putExtra("planetTextView", "Saturn");
        } else if (view == venusBtn) {
            quiz_intent.putExtra("planetTextView", "Venus");
        } else { // in case of unknown click, show log error and close activity
            Log.e("planets", "Unknown view clicked: " + view);
            finish();
        }

        startActivity(quiz_intent);
    }
}